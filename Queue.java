package test;

import java.util.ArrayList;

public class Queue {
	private ArrayList<String> queue ;
	public int queueSize;
	
	public Queue() {
		this.queue = new ArrayList<>();
		this.queueSize = 0;
	}
	
	public void pushBack(String element){
		queue.add(element);
		queueSize++;
		
	}
	
	public String popFront(){
		String element = queue.get(0);
		queue.remove(0);
		queueSize--;
		return element;
	}
	
	public void bubbleSort(){
		boolean thisStageHasExchanged = true;
		while (thisStageHasExchanged) {
			thisStageHasExchanged = false;
			for (int i = 0; i < queueSize-1; i++) {
				String frontTransactionType = queue.get(i).split(" ")[1];
				String backTransactionType = queue.get(i+1).split(" ")[1];
				if (frontTransactionType.equals("Withdraw") && backTransactionType.equals("Deposit")) {
					String temp = queue.get(i);
					queue.set(i, queue.get(i+1));
					queue.set(i+1, temp);
					thisStageHasExchanged = true;
				}
				else if(frontTransactionType.equals("Transfer") && backTransactionType.equals("Deposit")){
					String temp = queue.get(i);
					queue.set(i, queue.get(i+1));
					queue.set(i+1, temp);
					thisStageHasExchanged = true;
				}
				else if(frontTransactionType.equals("Transfer") && backTransactionType.equals("Withdraw")){
					String temp = queue.get(i);
					queue.set(i, queue.get(i+1));
					queue.set(i+1, temp);
					thisStageHasExchanged = true;
				}
			}
			
		}
	}

}
