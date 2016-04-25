package ar.com.inti;

public interface ImageTestFactory {
	ImageTest newTest(BillImage needle, BillImage candidateImage);
}
