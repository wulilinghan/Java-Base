package basic.enumtest;

public enum ERedisKey {
    KEY1{
        @Override
        public String toString() {
            return "KEY1";
        }
    },
    KEY2{
        @Override
        public String toString() {
            return "KEY2";
        }
    };
}
