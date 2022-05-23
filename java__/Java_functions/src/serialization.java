import java.io.*;

public class serialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        sample s = new sample();
        s.a = 5;

        File f = new File("obj.txt");


        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s);

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        sample k = (sample) ois.readObject();

        System.out.println(k.a);
    }
}

class sample implements Serializable{
    int a;
}