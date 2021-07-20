package math.simplecode;

/**
 * @Author: ThinkPad.
 * @Description: TODO()
 * @Date:Created in 2019/8/28.
 * @Modified By:
 */
public enum Handler {

    ADD() {
        @Override
        public int excute(int a, int b) {
            return a + b;
        }
    },
    SUBTRACTION() {
        @Override
        public int excute(int a, int b) {
            return a - b;
        }
    },
    MULT() {
        @Override
        public int excute(int a, int b) {
            return a * b;
        }
    },
    DIVISION() {
        @Override
        public int excute(int a, int b) {
            return a / b;
        }
    };

    public abstract int excute(int a, int b);
}
