package Ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class E08DataOutputStream {
	public static void main(String[] args) throws IOException {
		float j = (float) 98.98;
		double k = 581216732.323433;
		long num = 98982434244L;

		DataOutputStream ods = new DataOutputStream(new FileOutputStream("resources/ficheroDataStream.bin"));
		ods.writeFloat(j);
		ods.writeDouble(k);
		ods.writeLong(num);
		ods.close();

		j = 0;
		k = 0;
		num = 0;

		DataInputStream ids = new DataInputStream(new FileInputStream("resources/ficheroDataStream.bin"));

		j = ids.readFloat();
		k = ids.readDouble();
		num = ids.readLong();
		System.out.println(j + "\n" + k + "\n" + num);
		ids.close();
	}
}
