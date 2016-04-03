
public class TesteReplace {
	public static void main(String[] args) {

		String text = "olá !!! ;~~ como--;. você vai? .@;";
		text = text.replaceAll("[^a-zZ-Z1-9\u00C0-\u00FF ]", "").replaceAll("\\s+", " ").trim();
		System.out.println(text);
	}

	public String limpaString(String text) {
		text = text.replaceAll("[^a-zZ-Z1-9\u00C0-\u00FF ]", "").replaceAll("\\s+", " ").trim();
		return text;
	}

}
