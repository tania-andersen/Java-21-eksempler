/*
	Test af Java 21-faciliteter til Version2-artikel https://www.version2.dk/node/6989612 
	Testet med build 26. Kompiler & kør med flag:
	
	./java -version
	./java --source 21 --enable-preview TestJava21.java
*/

/* Unavngivne klasser */

void main() {
		
	/* Streng-skabeloner */

	String name = "Joan";
	String info = STR."My name is \{name}";
	assert info.equals("My name is Joan");   // true

	record NamedRectangle(String name, double width, double height) {
		double area() {
			return width * height;
		}
	}
  
	NamedRectangle[] zone = new NamedRectangle[] {
		new NamedRectangle("Alfa", 10, 20),
		new NamedRectangle("Bravo", 5, 10),
	};
  
	String table = java.util.FormatProcessor.FMT."""
		Description     Width    Height     Area
		%-12s\{zone[0].name}  %7.2f\{zone[0].width}  %7.2f\{zone[0].height}     %7.2f\{zone[0].area()}
		%-12s\{zone[1].name}  %7.2f\{zone[1].width}  %7.2f\{zone[1].height}     %7.2f\{zone[1].area()}
		\{" ".repeat(28)} Total %7.2f\{zone[0].area() + zone[1].area()}
		""";		
		
	System.out.println(table);
	
	/* SequencedSet-interface */
	
	var linkedHashSet = new java.util.LinkedHashSet();
	linkedHashSet.reversed().stream();
	
	/* Unavngivne mønstre og variabler */
	
	var q = new java.util.ArrayDeque<Integer>();
	q.add(1);
	int _ = q.remove();
	
	/* Records og mønster-match */

	record Point(int x, int y) {}
	
	var p = new Point(10, 5);
	
	if (p instanceof Point(int x, int y)) {
		System.out.println(x+y);
	}
		
	enum Color { RED, GREEN, BLUE }
	record ColoredPoint(Point p, Color c) {}
	record Rectangle(ColoredPoint upperLeft, ColoredPoint lowerRight) {}
	
	var r = new Rectangle(new ColoredPoint(p, Color.GREEN), new ColoredPoint(p, Color.BLUE));
	
	if (r instanceof Rectangle(ColoredPoint(Point x, Color c),
							   ColoredPoint lr)) {
		System.out.println(c);
	}

	if (r instanceof Rectangle(ColoredPoint(Point(var x, var y), var c),
							   var lr)) {
		System.out.println("Upper-left corner: " + x);
	}
}
