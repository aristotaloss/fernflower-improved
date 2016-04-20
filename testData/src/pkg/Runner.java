import org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler;

/**
 * Created by Bart on 4/20/2016.
 */
public class Runner {

	public static void main(String[] args) {
		ConsoleDecompiler.main(new String[] {"-mpm=10000", "-lit=1", "bin-test", "bin-test-out"});
	}

}
