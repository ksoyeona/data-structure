package cse12pa2student;

public class ArraySL implements StringList {

	String[] elements;
	int size;

	public ArraySL(String[] initialElements) {
		this.elements = initialElements;
		this.size = initialElements.length;
	}

	// Fill in all required methods here

	public String[] toArray() {

		String[] newList = new String[this.size];

		for (int i = 0; i < this.size; i++) {

			newList[i] = this.elements[i];
		}
		return newList;
	}

	public boolean isEmpty() {

		String[] newArray = this.toArray();

		for (int i = 0; i < newArray.length; i++) {

			if (!(newArray[i] == null)) {

				return false;
			}
		}

		return true;
	}

	public void transformAll(StringTransformer transformer) {

		for (int i = 0; i < elements.length; i++) {

			String s = elements[i];
			String str = transformer.transformElement(s);

			elements[i] = str;

		}
	}

	public void chooseAll(StringChooser sl) {

		for (int i = 0; i < this.size; i++) {

			if (false == sl.chooseElement(elements[0])) {

				i = 0;
			}

			if (i > 0 && false == sl.chooseElement(elements[i - 1])) {

				i = i - 1;
			}

			String str = this.elements[i];

			// when elements not in the end was skipped
			if ((false == sl.chooseElement(str)) && !(i == this.size - 1)) {

				elements[i] = elements[i + 1];

				this.size = this.size - 1;

				if (!(i == this.size)) {
					for (int k = 0; k < (this.size - i - 1); k++) {

						if (k < this.size - 1) {
							elements[i + k + 1] = elements[i + k + 2];
						}
					}
				}

				elements[this.size] = null;

				// if the last item is not chosen after current removal
				if (i == size - 1 && (false == sl.chooseElement(elements[this.size - 1]))) {

					elements[this.size - 1] = null;
					this.size = this.size - 1;
				}

				continue;
			}

			// if last element is not chosen
			if (i == this.size - 1 && false == sl.chooseElement(str)) {

				this.elements[i] = null;
				this.size = this.size - 1;

			}

		}

	}

}
