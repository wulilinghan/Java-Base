package effective.object;

public class ProviderImpl1 implements Provider {

    @Override
    public Service newInstance() {
        return new ServiceImpl1();
    }

}
