package top.b0x0.data_structure.map.p_001;

/**
 * hash因子碰撞率测试
 *
 * @author ManJiis
 * @since 2021-09-17
 * @since JDK1.8
 */
public class HashRateInfo {

    /**
     * 最大Hash
     */
    private int maxHash;
    /**
     * 最小Hash
     */
    private int minHash;
    /**
     * hash计算乘机因子
     */
    private int multiplier;
    /**
     * 碰撞数
     */
    private int collisionCount;
    /**
     * 碰撞比率
     */
    private double collisionRate;

    public HashRateInfo() {
    }

    public HashRateInfo(int maxHash, int minHash, int multiplier, int collisionCount, double collisionRate) {
        this.maxHash = maxHash;
        this.minHash = minHash;
        this.multiplier = multiplier;
        this.collisionCount = collisionCount;
        this.collisionRate = collisionRate;
    }

    public int getMaxHash() {
        return maxHash;
    }

    public void setMaxHash(int maxHash) {
        this.maxHash = maxHash;
    }

    public int getMinHash() {
        return minHash;
    }

    public void setMinHash(int minHash) {
        this.minHash = minHash;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getCollisionCount() {
        return collisionCount;
    }

    public void setCollisionCount(int collisionCount) {
        this.collisionCount = collisionCount;
    }

    public double getCollisionRate() {
        return collisionRate;
    }

    public void setCollisionRate(double collisionRate) {
        this.collisionRate = collisionRate;
    }

    @Override
    public String toString() {
        return "HashRateInfo{" +
                "maxHash=" + maxHash +
                ", minHash=" + minHash +
                ", multiplier=" + multiplier +
                ", collisionCount=" + collisionCount +
                ", collisionRate=" + collisionRate +
                '}';
    }
}
