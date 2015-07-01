package idv.xfl03.wzew.score.core;

public class Tile{
	public Type type;
	public boolean getType(){
		return type.getType();
	}
	public String getStringType(){
		return type.getStringType();
	}
	
	public String examCode;//0
	public String userName;//1
	public String userCode;
	public String userClass;
	public String examName;
	public String chinese;
	public String math;
	public String english;
	public String physics;
	public String chemistry;
	public String biology;
	public String politics;
	public String history;
	public String geography;
	public String it;//information technology
	public String gt;//general technology
	public String humanity;//humanities' total
	public String science;//sciences' total
	public String total;
	public static int getRank(String rank,int total){
		return getRank(Float.parseFloat(rank),total);
	}
	public static int getRank(float rank,int total){
		return (int)(total*(1-rank/100));
	}
	public void set(int i,String tt){
		switch(i){
		case 0:
			this.examCode=tt;
			break;
		case 1:
			this.userName=tt;
			break;
		case 2:
			this.userCode=tt;
			break;
		case 3:
			this.userClass=tt;
			break;
		case 4:
			this.examName=tt;
			break;
		case 5:
			this.chinese=tt;
			break;
		case 6:
			this.math=tt;
			break;
		case 7:
			this.english=tt;
			break;
		case 8:
			this.physics=tt;
			break;
		case 9:
			this.chemistry=tt;
			break;
		case 10:
			this.biology=tt;
			break;
		case 11:
			this.politics=tt;
			break;
		case 12:
			this.history=tt;
			break;
		case 13:
			this.geography=tt;
			break;
		case 14:
			this.it=tt;
			break;
		case 15:
			this.gt=tt;
			break;
		case 16:
			this.humanity=tt;
			break;
		case 17:
			this.science=tt;
			break;
		case 18:
			this.total=tt;
			break;
		}
	}
	public String get(int i){
		switch(i){
		case 0:
			return this.examCode;
		case 1:
			return this.userName;
		case 2:
			return this.userCode;
		case 3:
			return this.userClass;
		case 4:
			return this.examName;
		case 5:
			return this.chinese;
		case 6:
			return this.math;
		case 7:
			return this.english;
		case 8:
			return this.physics;
		case 9:
			return this.chemistry;
		case 10:
			return this.biology;
		case 11:
			return this.politics;
		case 12:
			return this.history;
		case 13:
			return this.geography;
		case 14:
			return this.it;
		case 15:
			return this.gt;
		case 16:
			return this.humanity;
		case 17:
			return this.science;
		case 18:
			return this.total;
		default:
			return "";
		}
	}
	public static String getName(int i){
		switch(i){
		case 0:
			return "���";
		case 1:
			return "����";
		case 2:
			return "ѧ��";
		case 3:
			return "�༶";
		case 4:
			return "��������";
		case 5:
			return "����";
		case 6:
			return "��ѧ";
		case 7:
			return "Ӣ��";
		case 8:
			return "����";
		case 9:
			return "��ѧ";
		case 10:
			return "����";
		case 11:
			return "����";
		case 12:
			return "��ʷ";
		case 13:
			return "����";
		case 14:
			return "��Ϣ";
		case 15:
			return "ͨ��";
		case 16:
			return "����";
		case 17:
			return "����";
		case 18:
			return "�ܷ�";
		default:
			return "";
		}
	}
}

class Type{
	public static final Type SCORE=new Type(true);
	public static final Type RANK=new Type(false);
	private boolean TYPE;
	private Type(boolean b){
		TYPE=b;
	}
	public boolean getType(){
		return TYPE;
	}
	public String getStringType(){
		return TYPE?"����":"����";
	}
}
