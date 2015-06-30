package idv.xfl03.wzew.score.core;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class PageParser {
	public ArrayList<Tile> parse(long id) throws Exception{
		return parse(""+id);
	}
	public ArrayList<Tile> parse(String id) throws Exception{
		String url="http://www.wzew.cn/manage/score.aspx?id="+id;
		//System.out.println(url);
		URL u=new URL(url);
		HttpURLConnection c=(HttpURLConnection) u.openConnection();
		Parser parser = new Parser(c);
		Node i2=null;
		for (NodeIterator i = parser.elements (); i.hasMoreNodes(); ) {
			Node node = i.nextNode();
			//System.out.println("1 toHtml:"+node.toHtml());
			//System.out.println("1 =================================================");
			if(node.toHtml().startsWith("<html xmlns=\"http://www.w3.org/1999/xhtml\">")){
				i2=node;
				break;
			}
		}
		NodeList n= i2.getChildren();
		for (SimpleNodeIterator i = n.elements ();
				i.hasMoreNodes(); ) {
			Node node = i.nextNode();
			//System.out.println("2 toHtml:"+node.toHtml());
			//System.out.println("2 =================================================");
			if(node.toHtml().startsWith("<body>")){
				i2=node;
				break;
			}
		}
		n= i2.getChildren();
		for (SimpleNodeIterator i = n.elements ();
				i.hasMoreNodes(); ) {
			Node node = i.nextNode();
			//System.out.println("3 toHtml:"+node.toHtml());
			//System.out.println("3 =================================================");
			if(node.toHtml().startsWith("<form ")){
				i2=node;
				break;
			}
		}
		n= i2.getChildren();
		for (SimpleNodeIterator i = n.elements ();
				i.hasMoreNodes(); ) {
			Node node = i.nextNode();
			//System.out.println("4 toHtml:"+node.toHtml());
			//System.out.println("4 =================================================");
			if(node.toHtml().startsWith("<div class=\"score_main\">")){
				i2=node;
				break;
			}
		}
		n= i2.getChildren();
		for (SimpleNodeIterator i = n.elements ();
				i.hasMoreNodes(); ) {
			Node node = i.nextNode();
			//System.out.println("5 toHtml:"+node.toHtml());
			//System.out.println("5 =================================================");
			if(node.toHtml().startsWith("<table width=\"100%\">")){
				i2=node;
				break;
			}
		}
		n= i2.getChildren();
		ArrayList<Node> aa=new ArrayList<Node>();
		for (SimpleNodeIterator i = n.elements ();
				i.hasMoreNodes(); ) {
			Node node = i.nextNode();
			//System.out.println("6 toHtml:"+node.toHtml());
			//System.out.println("6 =================================================");
			if(node.toHtml().startsWith("<tr>")&&node.toHtml().contains("</div>")){
				aa.add(node);
			}
		}
		ArrayList<Node> bb=new ArrayList<Node>();
		for(int i=0;i<aa.size();i++){
			Node t1=aa.get(i);
			//System.out.println("7 toHtml:"+t1.toHtml());
			//System.out.println("7 =================================================");
			NodeList t2=t1.getChildren();
			for (SimpleNodeIterator ii = t2.elements ();
					ii.hasMoreNodes(); ) {
				Node node = ii.nextNode();
				//System.out.println("8 toHtml:"+node.toHtml());
				//System.out.println("8 =================================================");
				if(node.toHtml().startsWith("<td>")){
					NodeList t3=node.getChildren();
					for (SimpleNodeIterator iii = t3.elements ();
							iii.hasMoreNodes(); ) {
						Node node2 = iii.nextNode();
						//System.out.println("9 toHtml:"+node2.toHtml());
						//System.out.println("9 =================================================");
						if(node2.toHtml().startsWith("<div>")){
							NodeList t4=node2.getChildren();
							for (SimpleNodeIterator iiii = t4.elements ();
									iiii.hasMoreNodes(); ) {
								Node node3 = iiii.nextNode();
								//System.out.println("10 toHtml:"+node3.toHtml());
								//System.out.println("10 =================================================");
								if(node3.toHtml().startsWith("<table ")){
									bb.add(node3);
								}
							}
						}
					}
				}
			}
		}
		ArrayList<Tile> tiles=new ArrayList<Tile>();
		TileParser parser1=new TileParser();
		for(int i=0;i<bb.size();i++){
			//System.out.println("11 toHtml:"+bb.get(i).toHtml());
			//System.out.println("11 =================================================");
			NodeList tt=bb.get(i).getChildren();
			for (SimpleNodeIterator ii = tt.elements ();
					ii.hasMoreNodes(); ) {
				Node node4 = ii.nextNode();
				//System.out.println("12 toHtml:"+node4.toHtml());
				//System.out.println("12 =================================================");
				if(node4.toHtml().startsWith("<tr align=\"center\">")){
					tiles.add(parser1.parse(node4,i==0?Type.SCORE:Type.RANK));
				}
			}
		}
		return tiles;
	}
}
