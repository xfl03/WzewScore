package idv.xfl03.wzew.score;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import idv.xfl03.wzew.score.core.PageParser;
import idv.xfl03.wzew.score.core.Tile;

public class ScoreTest {

	public static void main(String[] args) {
		try {
			System.out.println("请选择功能");
			for(int i=1;i<=14;i++){
				String fix=" ";
				if(i>=10)
					fix="";
				System.out.print(i+fix+"- 查询 "+Tile.getName(i+4)+" 成绩");
				if(i%2==0){
					System.out.println();
				}else{
					System.out.print(" ");
				}
			}
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			int input=Integer.parseInt(reader.readLine());
			query(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void query(int index) throws Exception{
		int iii=index+4;
		String subject=Tile.getName(iii);
		System.out.println("请输入考号以查询 "+subject+" 成绩和排名");
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Tile> tiles=(new PageParser()).parse(reader.readLine());
		boolean cut=false;
		for(int i=0;i<tiles.size();i++){
			Tile tile=tiles.get(i);
			if(i==0){
				System.out.println(tile.userClass+" "+tile.userName);
			}
			if(tile.getType())
				System.out.println(tile.examName+" "+subject+"  "+tile.getStringType()+" "+tile.get(iii));
			else{
				if(!cut){
					cut=true;
					System.out.println();
				}
				System.out.println(tile.examName+" "+subject+"  "+tile.getStringType()+" "+tile.get(iii)+"% ("+Tile.getRank(tile.get(iii),481)+")");
			}
		}
	}
	
}
