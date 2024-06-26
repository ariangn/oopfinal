package oopFinal;

public class HuffmanMessage extends EncryptedMessage{

	String name;
	String messageText;
	String oldMessage;
	
	private Encryptor encryptorUsed;
	
	public HuffmanMessage(String s) {
		messageText = s;
	}
	
	public HuffmanMessage(String n, String s) {
		name = n;
		messageText = s;
		encryptorUsed = new HuffmanEncryptor();
	}
	
	public HuffmanMessage(String n, String s, Encryptor e) {
		name = n;
		messageText = s;
		encryptorUsed = e;
	}
	
	@Override
	public String getMessageText() {
		return messageText;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public Encryptor getEncryptorUsed() {
		return encryptorUsed;
	}

	@Override
	public String getOldMessage() {
		return oldMessage;
	}

	@Override
	public void setOldMessage(String oldMessage) {
		this.oldMessage = oldMessage;
		
	}
	

}
