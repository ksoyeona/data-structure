package cse12pa2student;

class UpperCaseTransformer implements StringTransformer {

	public String transformElement(String s) {
		return s.toUpperCase();
	}

}

// Add your transformers here

class LowerCaseTransformer implements StringTransformer {

	public String transformElement(String s) {
		return s.toLowerCase();
	}
}

class heartTransformer implements StringTransformer {

	public String transformElement(String s) {

		String smile = "<3";
		String concatenated = s + smile;

		return concatenated;
	}
}