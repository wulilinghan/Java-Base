package math;

/**
 * Created By gao_e on 2020/5/19 15:30
 */
public class BitTest {

    public static void main(String[] args) {
        BitArray bitArray = new BitArray(15);
        bitArray.set(4, EBitValue.Bit_1);
        int value = bitArray.get(3).value();
        int value1 = bitArray.get(4).value();
        System.out.println(value);
        System.out.println(value1);
        System.out.println(bitArray.get(14).value());
        System.out.println(bitArray.get(15).value());
    }

}

class BitArray {

    private byte[] bytes = new byte[1];
    private int length = 8;
    private int outLength = 0;

    public int length() {
        return length;
    }

    public BitArray(int length) {
        if (length < 8) {
            this.length = 8;
            bytes = new byte[1];
            return;
        }
        length--;
        length |= length >> 1;
        length |= length >> 2;
        length |= length >> 4;
        length |= length >> 8;
        length |= length >> 16;
        length |= length >> 32;
        length++;
        this.length = length;
        int byteLen = this.length / 8;
        bytes = new byte[byteLen];
        outLength = length;
    }

    // TODO 待测试
    public void set(int index, EBitValue eBitValue) {
        if (index > outLength - 1)
            throw new IndexOutOfBoundsException();
        int bytesIndex = index >> 3;
        int less = index & 7; // 对8取余
        byte aByte = bytes[bytesIndex];
        int value = eBitValue.value();
        aByte |= value << less;
        bytes[bytesIndex] = aByte;
    }

    // TODO 待测试
    public EBitValue get(int index) {
        if (index > outLength - 1)
            throw new IndexOutOfBoundsException();
        int bytesIndex = index >> 3;// 除以8
        int less = index & 7; // 对8取余
        int value = (bytes[bytesIndex] >> less) & 1;
        return EBitValue.get(value);
    }
}

enum EBitValue {
    /**
     * 0
     */
    Bit_0(0),
    /**
     * 1
     */
    Bit_1(1);
    private int value;

    private EBitValue(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static EBitValue get(int value) {
        if (value == 0)
            return Bit_0;
        if (value == 1)
            return Bit_1;
        throw new IllegalArgumentException("value error , values is :" + value);
    }
}
