import java.util.ArrayList;
import java.util.List;

public class Test {

	static List<Block> blockChain = new ArrayList<Block>();
	
	public static int difficulty = 5;

	public static void main(String[] args) {

		addBlock(new Block("First Block", "0"));
		addBlock(new Block("Second Block", blockChain.get(blockChain.size() - 1).currentHash));
		addBlock(new Block("Third Block", blockChain.get(blockChain.size() - 1).currentHash));

		
		
		addBlock(new Block("Thief Block",blockChain.get(blockChain.size() - 2).currentHash));
		
		for (Block b : blockChain) {
			System.out.println(b);
		}

	}

	private static boolean isBlockChainValid(List<Block> blockChain) {
		if (blockChain.size() > 1) {
			for (int i = 1; i <= blockChain.size()-1; i++) {
				Block currentBlock = blockChain.get(i-1);
				Block nextBlock = blockChain.get(i);
				if (!(nextBlock.previousHash.equals(currentBlock.currentHash))) {
					return false;
				}
			}
		}
		return true;
	}

	public static void addBlock(Block b) {
		b.mineBlock(difficulty);
		blockChain.add(b);
		if(!isBlockChainValid(blockChain)) {
		   System.out.println("Block :"+b.currentHash + " - " +  b.previousHash +" is not valid, removing it");
			blockChain.remove(b);	
		}
	}

}
