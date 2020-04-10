package springbootdemo.model;;

public class Sudo {
	
	int[][] inputArray=new int[9][9];

	public int[][] getInputArray() {
		return inputArray;
	}

	

	public void setInputArray(int[][] inputArray2) {
		
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				this.inputArray[i][j]=inputArray2[i][j];
			}
		}
		// TODO Auto-generated method stub
		
	}

}
