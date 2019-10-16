import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class MyXmlHandler extends DefaultHandler  {

    private String lastElementName;
  //  private String name, type, weight;

    @Override
    public void startElement(String namespaceURI,
                             String lname,
                             String qname,
                             Attributes attrs){
        if (qname.equals("contact") && attrs != null){
            for (int i = 0; i <attrs.getLength() ; i++) {
                System.out.println(attrs.getLocalName(i) +" "+ attrs.getValue(i));
            }
//            type = attrs.getValue("type");
//            weight = attrs.getValue("weight");
        }
        lastElementName = qname;
    }

    public void characters(char[] ch, int start, int length)  {
        String information = new String(ch, start, length);
        information = information.replace("\n", "").trim();

        if (!information.isEmpty()) {
            if (lastElementName.equals("lastName")) {
                System.out.println(information);
 //               name = information;
            }
        }
    }

//    @Override
//    public void endElement(String uri, String localName, String qName)  {
//       // if ( (name != null && type != null && weight != null) ) {
//        if (qName.equals("contact")){
//            System.out.println(name + " " + type + " " + weight);
//            name = null;
//            type = null;
//            weight = null;
//        }
//    }
    }
