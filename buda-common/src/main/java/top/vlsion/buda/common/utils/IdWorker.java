package top.vlsion.buda.common.utils;

/**
 * 分布式ID生成by雪花算法
 * 雪花算法是 64 位 的二进制，一共包含了四部分：
 * 1位是符号位，也就是最高位，始终是0，没有任何意义，因为要是唯一计算机二进制补码中就是负数，0才是正数。
 * 41位是时间戳，具体到毫秒，41位的二进制可以使用69年，因为时间理论上永恒递增，所以根据这个排序是可以的。
 * 10位是机器标识，可以全部用作机器ID，也可以用来标识机房ID + 机器ID，10位最多可以表示1024台机器。
 * 12位是计数序列号，也就是同一台机器上同一时间，理论上还可以同时生成不同的ID，12位的序列号能够区分出4096个ID。
 *
 * @author : zhanghuang
 * @date : 2022-01-14 15:58
 */
public class IdWorker {
    //由于时间戳只能用69年，我们的计时又是从1970年开始的，所以这个twepoch表示从项目开始的时间，用生成ID的时间减去twepoch作为时间戳，可以使用更久。
    private final long twepoch = 1642147307292L;
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;

    //最大机器号32以内
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits); //31

    //最大机房号32以内
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); //31

    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    //限制序列的范围
    private final long sequenceMask = -1L ^ (-1L << sequenceBits); //4095

    //机器ID
    private long workerId;

    //机房ID
    private long datacenterId;

    //一毫秒内生成的ID最新序号
    private long sequence = 0L;

    private long lastTimestamp = -1L;


    public IdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        //将当前时间戳左移，放到41bit； 机房ID左移，放到5bit；机器ID左移，放到5bit；拼接上序列号 转成10进制就是long型
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(0, 0);
        for (int i = 0; i < 100; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
        }
    }
}
