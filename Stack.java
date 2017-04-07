package test;

import java.util.ArrayList;

public class Stack {
	private ArrayList<String> stack ;
	public int stackSize;
	
	public Stack() {
		this.stack = new ArrayList<>();
		this.stackSize = 0;
	}
	
	public void push(String element){
		stack.add(element);
		stackSize++;
		
	}
	
	public String pop(){
		String element = stack.get(stack.size()-1);
		stack.remove(stack.size()-1);
		stackSize--;
		return element;
	}
	public void bubbleSort(){
		boolean thisStageHasExchanged = true;
		while (thisStageHasExchanged) {
			thisStageHasExchanged = false;
			for (int i = 0; i < stackSize-1; i++) {
				String frontTransactionType = stack.get(i).split(" ")[1];
				String backTransactionType = stack.get(i+1).split(" ")[1];
				if (frontTransactionType.equals("Withdraw") && backTransactionType.equals("Deposit")) {
					String temp = stack.get(i);
					stack.set(i, stack.get(i+1));
					stack.set(i+1, temp);
					thisStageHasExchanged = true;
				}
				else if(frontTransactionType.equals("Transfer") && backTransactionType.equals("Deposit")){
					String temp = stack.get(i);
					stack.set(i, stack.get(i+1));
					stack.set(i+1, temp);
					thisStageHasExchanged = true;
				}
				else if(frontTransactionType.equals("Transfer") && backTransactionType.equals("Withdraw")){
					String temp = stack.get(i);
					stack.set(i, stack.get(i+1));
					stack.set(i+1, temp);
					thisStageHasExchanged = true;
				}
			}
			
		}
	}

}
