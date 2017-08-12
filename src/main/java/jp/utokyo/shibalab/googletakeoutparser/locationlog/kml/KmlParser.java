package jp.utokyo.shibalab.googletakeoutparser.locationlog.kml;

/**
 * 
 */
public class KmlParser {

	/*
	 * @param args
	 
	public static void main(String[] args) {
		File kmlFile    = new File(args[0]);
		File outputFile = new File(args[1]);
		
		try (BufferedWriter bw=new BufferedWriter(new FileWriter(outputFile))) {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder        docBuilder = docFactory.newDocumentBuilder();
			DOMBuilder             domBuilder = new DOMBuilder();
			
			Document doc = domBuilder.build( docBuilder.parse(kmlFile) );
			
			List<String[]> output = parseJDOM(doc);
			bw.write("time,lon,lat,alt");
			bw.newLine();
			for(String[] tokens: output) { 
				bw.write(StringUtils.join(tokens,","));
				bw.newLine();
			}
			
		}
		catch(ParserConfigurationException|IOException|SAXException exp) {
			exp.printStackTrace();
		}
	}
	
	protected static Namespace KML_NS = Namespace.getNamespace("http://www.opengis.net/kml/2.2");
	protected static Namespace GX_NS  = Namespace.getNamespace("gx","http://www.google.com/kml/ext/2.2");
	
	public static List<String[]> parseJDOM(Document doc) {
		Element root      = doc.getRootElement(); // KML
		Element document  = root.getChild("Document", KML_NS);
		List<Element> placemarks = document.getChildren("Placemark",KML_NS);
		
		
		List<String[]> output = new ArrayList<String[]>();
		for(Element placemark:placemarks) {
			Element gxTrack = placemark.getChild("Track",GX_NS);System.out.println(gxTrack);
			List<Element> whens  = gxTrack.getChildren("when",KML_NS);
			List<Element> coords = gxTrack.getChildren("coord",GX_NS);
			
			int N = whens.size();
			for(int i=0;i<N;i++) {
				String   time  = whens.get(i).getText().replace("T"," ").replace("Z","");
				String[] coord = coords.get(i).getText().split(" ");
				
				output.add(new String[]{time,coord[0],coord[1],coord[2]});
			}
			
		}
		
		Collections.sort(output,new Comparator<String[]>() {
			public int compare(String[] a,String[] b) {
				return a[0].compareTo(b[0]);
			}
		});
		
		return output;
	}
	*/

}
