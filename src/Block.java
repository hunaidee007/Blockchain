import java.util.Date;

public class Block {
	
	public String currentHash;
	public String previousHash; 
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	
	//Block Constructor.  
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.currentHash = calculateHash();
	}
	
	public String calculateHash() {
		String calculatedhash = HashUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	//Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); 
		while(!currentHash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			currentHash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + currentHash+" : " + this.data);
	}

	@Override
	public String toString() {
		return "{ \n\t currentHash=" + currentHash + "\n\t previousHash=" + previousHash + "\n\t data=" + data
				+ "\n\t timeStamp=" + timeStamp + "\n\t nonce="+nonce+"\n}";
	}
	
	
	
}