package idv.xfl03.wzew.score.core;

import java.util.ArrayList;

import org.htmlparser.Node;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class TileParser {
	public Tile parse(Node node,Type t){
		Tile tile=new Tile();
		tile.type=t;
		NodeList list=node.getChildren();
		ArrayList<Node> nodes=new ArrayList<Node>();
		for (SimpleNodeIterator i = list.elements ();
				i.hasMoreNodes(); ) {
			Node node2 = i.nextNode();
			//System.out.println("12 toHtml:"+node2.toHtml());
			//System.out.println("12 =================================================");
			if(node2.toHtml().startsWith("<td")){
				nodes.add(node2);
			}
		}
		for(int i=0;i<nodes.size();i++){
			Node node3=nodes.get(i);
			String tt=clean(node3.toPlainTextString());
			//System.out.println("13 "+i+" toPlainTextString:"+tt);
			//System.out.println("13 =================================================");
			tile.set(i, tt);
		}
		return tile;
	}
	private String clean(String text){
		return text.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "").trim();
	}
}
