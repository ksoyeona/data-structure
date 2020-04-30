package cse12pa2student;

class LongWordChooser implements StringChooser {

	@Override
	public boolean chooseElement(String s) {
		return s.length() > 5;
	}

}

//chooses string whose length is shorter or equal to 5
class ShortWordChooser implements StringChooser {

	@Override
	public boolean chooseElement(String s) {
		return s.length() <= 5;
	}

}

//chooses string that starts with "a"
class SpecificWordChooser implements StringChooser {

	@Override
	public boolean chooseElement(String s) {
		
		String str = "a";
		return s.startsWith(str) ==true;
	}

}
