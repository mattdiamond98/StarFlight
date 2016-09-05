package net.swordsofvalor.starflight.Serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.newdawn.slick.Input;

public class SerializableInput implements Serializable {

	private static final long serialVersionUID = -1606348271032325071L;
	public Input input;
	
	public SerializableInput(Input input) {
		this.input = input;
	}
	
	public static SerializableInput fromString(String s) throws IOException, ClassNotFoundException {
		byte [] data = Base64Coder.decode(s);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
		Object o = ois.readObject();
		ois.close();
		if (o instanceof SerializableInput) return (SerializableInput) o;
		else return null;
	}
	
	public String toString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(Base64Coder.encode(baos.toByteArray()));
	}
}
