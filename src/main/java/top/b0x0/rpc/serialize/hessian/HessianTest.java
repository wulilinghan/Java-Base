package top.b0x0.rpc.serialize.hessian;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import top.b0x0.rpc.serialize.Person;

public class HessianTest {

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        Person lisi = new Person(2, "LiSi");
        ho.writeObject(lisi);
        byte[] lisiByte = os.toByteArray();
        ho.close();
        os.close();
        
        ByteArrayInputStream is = new ByteArrayInputStream(lisiByte);
        HessianInput hi = new HessianInput(is);
        Person person = (Person)hi.readObject();
        System.out.println(person);
        hi.close();
        is.close();
        
    }

}
