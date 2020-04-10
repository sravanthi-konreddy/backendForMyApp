package springbootdemo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootdemo.model.Sudo;

//import com.demo.model.Book;
//import com.demo.model.Sudo;
//import com.demo.model.User;

@RestController
public class SudokuController {
	
	private static TreeMap<String, List<Integer>> map = new TreeMap<String, List<Integer>>();
	private static List<Integer> list1 ;
	private static List<String> fixedList;
	private static List<String> marked;
	private static int val1;
	private static int val2;
	private static boolean toSolve;
	
	@RequestMapping(value="/solveSudoku",method=RequestMethod.POST)
	public ResponseEntity<Sudo> solveSudoku(@RequestBody Sudo sudo){
		System.out.println("In Spring boot demo!!!!!!!");
		int[][] inputArray = new int[9][9];
		int[][] sudoInputArray=sudo.getInputArray();
		
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				inputArray[i][j]=sudoInputArray[i][j];
			}
		}
		System.out.println("hello!!!!!!!!!!!!!!!!!!!!1112223");
		list1 = new ArrayList<Integer>();
		fixedList = new ArrayList<String>();
		marked = new ArrayList<String>();
		val1 = 0;
		val2 = 0;
		toSolve = false;
		

		
		for (int i = 1; i <= 9; i++) {
			list1.add(i);
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)

			{
				List<Integer> list2 = new ArrayList<Integer>();
				list2.addAll(list1);
				map.put(String.valueOf(i) + String.valueOf(j), list2);
			}
		}

		findFixed(inputArray);
System.out.println("Partial Solution::::");

		for (int[] row : inputArray) {
			System.out.println(Arrays.toString(row));
		}
		calculateTrialAndError(inputArray);

		System.out.println(
				":::::::::::::::::::::::::::::::::::::::::After Trial and Error:::::::::::::::::::::::::::::::::::::::");

		for (int[] row : inputArray) {
			System.out.println(Arrays.toString(row));
		}

		

		findFixed(inputArray);
		/*for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)

			{
				List<Integer> list2 = new ArrayList<Integer>();
				list2.addAll(list1);
				if (map.containsKey(String.valueOf(i) + String.valueOf(j))) {
					map.get(String.valueOf(i) + String.valueOf(j)).clear();
					map.get(String.valueOf(i) + String.valueOf(j)).addAll(list2);
				}
			}
		}*/
		Sudo sud=new Sudo();
		sud.setInputArray(inputArray);
		
		return ResponseEntity.ok().body(sud);
	}
	
	private static void calculateTrialAndError(int[][] inputArray) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map.containsKey(String.valueOf(i) + String.valueOf(j))) {
					Collections.sort(map.get(String.valueOf(i) + String.valueOf(j)));
				}
			}
		}
		solve(inputArray);

	}

	private static void solve(int[][] inputArray) {

		List<Integer> list2 = new ArrayList<Integer>();
		list2.addAll(list1);
		System.out.println("Hi");
		int temp = 0;
		int temp1 = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = temp1; j < 9; j++) {

				temp = j;
				if (map.containsKey(String.valueOf(i) + String.valueOf(j))) {

					map.get(String.valueOf(i) + String.valueOf(j)).clear();
					map.get(String.valueOf(i) + String.valueOf(j)).addAll(list2);
					calculatePossibilities(inputArray, i, j);
					if (map.get(String.valueOf(i) + String.valueOf(j)).size() > 0 && inputArray[i][j] == 0) {
						inputArray[i][j] = map.get(String.valueOf(i) + String.valueOf(j)).get(0);
					} else {
						backtrack(inputArray, i, j, true);
						toSolve = false;
						temp = j;
						int i1 = i;
						int j1 = temp + 1;
						for (i = i1; i < 9; i++) {
							for (j1 = temp + 1; j1 < 9; j1++) {
								if (map.containsKey(String.valueOf(i) + String.valueOf(j1))) {
									map.get(String.valueOf(i) + String.valueOf(j1)).clear();
									map.get(String.valueOf(i) + String.valueOf(j1)).addAll(list2);
									calculatePossibilities(inputArray, i, j1);
								}
							}

							temp = -1;
						}
						i = i1;

					}
				}
			}

		}

	}

	private static Object backtrack(int[][] inputArray, int i, int j, boolean fromSolve) {
		if (fromSolve) {
			val1 = i;
			val2 = j;
		}
		List<Integer> list2 = new ArrayList<Integer>();
		if (toSolve)
			return null;
		list2.addAll(list1);
		int flag = 0;
		int flag1 = 0;
		int k1 = 0, l1 = 0;
		int temp = j;
		int temp3 = 0;
		int temp4 = 0;
		for (k1 = i; k1 >= 0; k1--) {
			flag1 = 0;
			for (l1 = j - 1; l1 >= 0; l1--) {
				if (map.containsKey(String.valueOf(k1) + String.valueOf(l1))) {
					if (map.get(String.valueOf(k1) + String.valueOf(l1)).size() > 1) {
						map.get(String.valueOf(k1) + String.valueOf(l1)).remove(0);
						inputArray[k1][l1] = map.get(String.valueOf(k1) + String.valueOf(l1)).get(0);
					} else {
						continue;
					}
					int temp1 = 0, temp2 = 0;
					int t1 = k1;
					int t2 = l1;
					int m = k1;
					int n = l1 + 1;
					// int m=0,n=0;
					int ctemp1 = k1;
					int ctemp = l1;
					int m1 = ctemp1;
					int n1 = ctemp;

					for (m1 = t1; m1 <= val1; m1++) {
						for (n1 = t2 + 1; n1 < 9; n1++) {
							if (map.containsKey(String.valueOf(m1) + String.valueOf(n1))) {
								if ((m1 < val1 && n1 <= 8) || (m1 == val1 && n1 <= val2)) {
									inputArray[m1][n1] = 0;
								} else {
									flag1 = 1;
									break;
								}
							}

						}
						if (flag1 == 1) {
							flag1 = 0;
							break;
						}

						t2 = -1;
					}

					for (m = ctemp1; m <= val1; m++) {
						flag1 = 0;
						flag = 0;
						for (n = ctemp + 1; n <= 8; n++) {
							if ((m < val1 && n <= 8) || (m == val1 && n <= val2)) {
								if (map.containsKey(String.valueOf(m) + String.valueOf(n))) {

									map.get(String.valueOf(m) + String.valueOf(n)).clear();
									map.get(String.valueOf(m) + String.valueOf(n)).addAll(list2);
									calculatePossibilities(inputArray, m, n);

									if (map.get(String.valueOf(m) + String.valueOf(n)).size() >= 1) {
										inputArray[m][n] = map.get(String.valueOf(m) + String.valueOf(n)).get(0);
										if (m == val1 && n == val2) {
											flag1 = 1;
											toSolve = true;
											return null;
										}
										ctemp1 = m;
										ctemp = n;

									} else {

										backtrack(inputArray, m, n, false);
										if (toSolve) {
											return null;
										}
										continue;

									}
								}
							} else {
								flag1 = 2;
							}

						}
						if (flag1 == 2)
							break;
						ctemp = -1;
					}
					if (flag1 == 2)
						break;

				}
				if (flag1 == 2)
					break;

			}
			j = 9;

		}
		return null;

	}

	private static void findFixed(int[][] inputArray) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (inputArray[i][j] != 0) {
					fixedList.add(String.valueOf(i) + String.valueOf(j));
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (inputArray[i][j] == 0) {
					calculatePossibilities(inputArray, i, j);
				} else {
					map.remove(String.valueOf(i) + String.valueOf(j));
				}

			}
		}
		int count = 0;
		while (true) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (inputArray[i][j] == 0) {
						if (map.containsKey(String.valueOf(i) + String.valueOf(j)))
							if (map.get(String.valueOf(i) + String.valueOf(j)).size() == 1) {
								inputArray[i][j] = map.get(String.valueOf(i) + String.valueOf(j)).get(0);
								map.remove(String.valueOf(i) + String.valueOf(j));
								count++;
							}
					}

				}
			}
			if (count == 0) {
				break;
			}
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (inputArray[i][j] == 0) {
						calculatePossibilities(inputArray, i, j);
					} else {
						map.remove(String.valueOf(i) + String.valueOf(j));
					}

				}
			}
			count = 0;
		}
		return;
	}
	private static void calculatePossibilities(int[][] inputArray, int i, int j) {
		int a = 0, b = 0, c = 0, d = 0;
		if (i == 0 || i == 1 || i == 2) {
			if (i == 0) {
				a = 1;
				b = 2;
			} else if (i == 1) {
				a = 0;
				b = 2;
			} else if (i == 2) {
				a = 0;
				b = 1;
			}
		} else if (i == 3 || i == 4 || i == 5) {
			if (i == 3) {
				a = 4;
				b = 5;
			} else if (i == 4) {
				a = 3;
				b = 5;
			} else if (i == 5) {
				a = 3;
				b = 4;
			}
		} else if (i == 6 || i == 7 || i == 8) {
			if (i == 6) {
				a = 7;
				b = 8;
			} else if (i == 7) {
				a = 6;
				b = 8;
			} else if (i == 8) {
				a = 6;
				b = 7;
			}
		}

		if (j == 0 || j == 1 || j == 2) {
			if (j == 0) {
				c = 1;
				d = 2;
			} else if (j == 1) {
				c = 0;
				d = 2;
			} else if (j == 2) {
				c = 0;
				d = 1;
			}
		} else if (j == 3 || j == 4 || j == 5) {
			if (j == 3) {
				c = 4;
				d = 5;
			} else if (j == 4) {
				c = 3;
				d = 5;
			} else if (j == 5) {
				c = 3;
				d = 4;
			}
		} else if (j == 6 || j == 7 || j == 8) {
			if (j == 6) {
				c = 7;
				d = 8;
			} else if (j == 7) {
				c = 6;
				d = 8;
			} else if (j == 8) {
				c = 6;
				d = 7;
			}
		}

		if (map.containsKey(String.valueOf(i) + String.valueOf(j))) {
			for (int k = 0; k < 9; k++) {
				if (map.get(String.valueOf(i) + String.valueOf(j)).contains(new Integer(inputArray[i][k]))) {
					map.get(String.valueOf(i) + String.valueOf(j)).remove(new Integer(inputArray[i][k]));
				}
				if (map.get(String.valueOf(i) + String.valueOf(j)).contains(new Integer(inputArray[k][j]))) {
					map.get(String.valueOf(i) + String.valueOf(j)).remove(new Integer(inputArray[k][j]));
				}
			}
			if (map.get(String.valueOf(i) + String.valueOf(j)).contains(new Integer(inputArray[a][c]))) {
				map.get(String.valueOf(i) + String.valueOf(j)).remove(new Integer(inputArray[a][c]));
			}
			if (map.get(String.valueOf(i) + String.valueOf(j)).contains(new Integer(inputArray[a][d]))) {
				map.get(String.valueOf(i) + String.valueOf(j)).remove(new Integer(inputArray[a][d]));
			}
			if (map.get(String.valueOf(i) + String.valueOf(j)).contains(new Integer(inputArray[b][c]))) {
				map.get(String.valueOf(i) + String.valueOf(j)).remove(new Integer(inputArray[b][c]));
			}
			if (map.get(String.valueOf(i) + String.valueOf(j)).contains(new Integer(inputArray[b][d]))) {
				map.get(String.valueOf(i) + String.valueOf(j)).remove(new Integer(inputArray[b][d]));
			}
		}

		return;

	}
}
