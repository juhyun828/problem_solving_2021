import java.io.*;
import java.util.*;
// 210328

public class Main_BJ_10825_국영수 {
	
	static class Student implements Comparable<Student>{
		String name;
		int korean, english, math;
		
		public Student(String name, int korean, int english, int math) {
			this.name = name;
			this.korean = korean;
			this.english = english;
			this.math = math;
		}

		@Override
		public int compareTo(Student o) {
			if(this.korean == o.korean) {
				if(this.english == o.english) {
					if(this.math == o.math) {
						return this.name.compareTo(o.name);
					}
					return Integer.compare(this.math, o.math) * -1;
				}
				return Integer.compare(this.english, o.english);
			}	
			return Integer.compare(this.korean, o.korean) * -1;
		}
	}
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input_BJ_10825_국영수.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = stoi(br.readLine());
		Student[] students = new Student[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			students[i] = new Student(st.nextToken(), 
					stoi(st.nextToken()), stoi(st.nextToken()), stoi(st.nextToken()));
		}
		
		Arrays.sort(students);
	
		for(int i=0; i<N; i++) {
			sb.append(students[i].name + "\n");
		}
		System.out.println(sb.toString());

		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}

