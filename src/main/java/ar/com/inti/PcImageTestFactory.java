package ar.com.inti;


public class PcImageTestFactory implements ImageTestFactory {

	@Override
	public PcImageTest newTest(BillImage needle, BillImage candidateImage) {
		return new PcImageTest(needle, candidateImage);
	}
}
