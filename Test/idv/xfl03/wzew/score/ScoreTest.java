package idv.xfl03.wzew.score;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import idv.xfl03.wzew.score.core.PageParser;
import idv.xfl03.wzew.score.core.Tile;

public class ScoreTest {
	
	public static final String VERSION="1.0.0";
	public static final int TOTAL_STUDENTS=481;

	public static void main(String[] args) {
		try {
			System.out.println("请选择功能");
			for(int i=1;i<=14;i++){
				String fix=" ";
				if(i>=10)
					fix="";
				System.out.print(i+fix+"- 查询 "+Tile.getName(i+4)+" 成绩");
				if(i%3==0){
					System.out.println();
				}else{
					System.out.print(" ");
				}
			}
			System.out.println("15- 查询各科排名");
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			int input=Integer.parseInt(reader.readLine());
			if(input<14)
				query(input);
			else
				an();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void query(int index) throws Exception{
		int iii=index+4;
		String subject=Tile.getName(iii);
		System.out.println("请输入考号以查询 "+subject+" 成绩和排名");
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		String input=reader.readLine();
		ArrayList<Tile> tiles=(new PageParser()).parse(input);
		if(tiles.size()==0){
			System.out.println("考号 "+input+" 不存在");
			System.out.println();
			query(index);
			return;
		}
		
		boolean cut=false;
		
		float scoreTotal=0;
		int number=0;
		float scoreTop=0;
		float scoreMin=100;
		
		float rankTotal=0;
		//int rankNumber=0;
		float rankTop=0;
		float rankMin=100;
		
		for(int i=0;i<tiles.size();i++){
			Tile tile=tiles.get(i);
			if(i==0){
				System.out.println(tile.userClass+" "+tile.userName);
			}
			if(tile.getType()){
				float score=(tile.get(iii)!=null&&!tile.get(iii).equalsIgnoreCase("&nbsp;"))?Float.parseFloat(tile.get(iii)):0;
				if(score>scoreTop)
					scoreTop=score;
				if(score<scoreMin&&score!=0)
					scoreMin=score;
				if(score!=0){
					scoreTotal+=score;
					number++;
				}
				System.out.println(tile.examName+" "+subject+"  "+tile.getStringType()+" "+score);
			}
			else{
				float rank=(tile.get(iii)!=null&&!tile.get(iii).equalsIgnoreCase("&nbsp;"))?Float.parseFloat(tile.get(iii)):0;
				if(rank>rankTop)
					rankTop=rank;
				if(rank<rankMin&&rank!=0)
					rankMin=rank;
				if(!cut){
					cut=true;
					System.out.println("最高分 "+scoreTop+" 最低分 "+scoreMin+" 极差 "+(scoreTop-scoreMin));
					System.out.println("平均分  "+parse((scoreTotal/number)));
					System.out.println();
				}
				if(rank!=0){
					rankTotal+=rank;
					//rankNumber++;
				}
				System.out.println(tile.examName+" "+subject+"  "+tile.getStringType()+" "+rank+"% ("+Tile.getRank(rank,TOTAL_STUDENTS)+")");
			}
		}
		float averageRank=(float)Math.round((rankTotal/number)*10)/10;
		System.out.println("最高排名 "+rankTop+"% ("+Tile.getRank(rankTop,TOTAL_STUDENTS)+") 最低排名 "+rankMin+"% ("+Tile.getRank(rankMin,TOTAL_STUDENTS)+") 极差 "+((float)(Math.round((rankTop-rankMin)*10))/10)+"% ("+(int)((rankTop-rankMin)/100*TOTAL_STUDENTS)+")");
		System.out.println("平均排名  "+averageRank+"% ("+Tile.getRank(averageRank,TOTAL_STUDENTS)+")");
	}
	private static void an() throws Exception{
		System.out.println("请输入考号以查询所有排名");
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		String input=reader.readLine();
		ArrayList<Tile> tiles=(new PageParser()).parse(input);
		if(tiles.size()==0){
			System.out.println("考号 "+input+" 不存在");
			System.out.println();
			an();
			return;
		}
		float[] ranks=new float[11];
		float[] times=new float[11];
		float[] scores=new float[11];
		int[] list=new int[11];
		for(int i=0;i<tiles.size();i++){
			Tile tile=tiles.get(i);
			if(i==0)
				System.out.println(tile.userClass+" "+tile.userName);
			if(tile.getType()){
				for(int j=0;j<11;j++){
					float score=(tile.get(j+5)!=null&&!tile.get(j+5).equalsIgnoreCase("&nbsp;"))?Float.parseFloat(tile.get(j+5)):0;
					if(score==0)
						continue;
					scores[j]+=score;
					times[j]++;
				}
			}else{
				for(int j=0;j<11;j++){
					float rank=(tile.get(j+5)!=null&&!tile.get(j+5).equalsIgnoreCase("&nbsp;"))?Float.parseFloat(tile.get(j+5)):0;
					if(rank==0)
						continue;
					ranks[j]+=rank;
				}
			}
		}
		for(int i=0;i<11;i++){
			ranks[i]/=times[i];
			ranks[i]=parse(ranks[i]);
			scores[i]/=times[i];
			scores[i]=parse(scores[i]);
		}
		for(int i=0;i<11;i++){
			int index=0;
			for(int j=0;j<11;j++){
				if(j==i)
					continue;
				if(ranks[j]>ranks[i])
					index++;
			}
			list[index]=i;
		}
		System.out.println("科目    平均名次     平均分");
		for(int i=0;i<11;i++){
			int index=list[i];
			System.out.println(Tile.getName(index+5)+" "+parseRank(ranks[index])+" "+scores[index]);
		}
	}
	private static float parse(float f){
		return (float)Math.round(f*10)/10;
	}
	private static String parseRank(float rank){
		return rank+"% ("+Tile.getRank(rank, TOTAL_STUDENTS)+")";
	}
}
