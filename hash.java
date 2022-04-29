package ipl;

public class hash {
    static String getHashed(String s){
        int sum=0;
        if(s.length()==1){sum = s.charAt(0);} //stadiums
        else{s.toUpperCase();sum=(s.charAt(0)+33*s.charAt(1));} //teams
        int pbks=2258,gt=2843,rr=2788,dc=2278,srh=2789,kkr=2550,rcb=2293,lsg=2815,csk=2806,mi=2486;
        int brabourne=66,wankhede=87,dy=68,pune=80;
        switch (sum){
            case 66: return "Brabourne-CCI Stadium, Mumbai";
            case 87: return "Wankhede Stadium, Mumbai";
            case 68: return "DY Patil Stadium, Mumbai";
            case 80: return "MCA Stadium, Pune";
            case 2258: return "Punjab Kings";
            case 2843: return "Gujarat Titans";
            case 2278: return "Delhi Capitals";
            case 2789: return "Sunrisers Hyderabad";
            case 2550: return "Kolkata Knight Riders";
            case 2293: return "Royal Challengers Bangalore";
            case 2815: return "Lucknow SuperGiants";
            case 2806: return "Chennai Super Kings";
            case 2486: return "Mumbai Indians";
            default: return null;
        }
    }
}
