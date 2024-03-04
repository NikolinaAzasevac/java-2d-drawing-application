package introduction;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello world");
		System.out.println("Hello world");
		int prviBroj = 7;
		int drugiBroj;
		drugiBroj=2;
		System.out.println(prviBroj/drugiBroj);
		
		float treciBroj = 5.3f;
		double cetvrtiBroj=5.1;
		System.out.println(treciBroj/cetvrtiBroj); //float ide u double pa je rey double
		
		int petiBroj = 9;
		System.out.println(petiBroj/cetvrtiBroj);
		int rezultat =(int)(petiBroj/cetvrtiBroj); //eks konverzija, iz veceg u manje
		System.out.println(rezultat);
		
		boolean first = true;
		boolean second = false;
		System.out.println(first && second);
		System.out.println(first || second);
		System.out.println(!first);
		
		char ch = '\n';
		String prviString = "abc";
		String drugiString = new String ("def");
		System.out.println(prviString+drugiString);
		System.out.println(prviString.concat(drugiString));
		System.out.println(prviString+ch);
		
		String stringKaoBroj = "5";
		System.out.println(Integer.parseInt(stringKaoBroj)+prviBroj);
		
		
		
		
	}

}
