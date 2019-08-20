package com.solnamu.yb.util;

public class PagingCount {
	int links=10;
	int limit = 10;
	int totalPage = 0;
	
	public PagingCount() {
		super();
	}
	
	public PagingCount(int numrows) {
		super();
		this.totalPage = ((numrows-1)/limit)+1;
	}
	
	public PagingCount(int numrows, int links, int limit) {
		super();
		this.links = links;
		this.limit = limit;
		this.totalPage = ((numrows-1)/limit)+1;
	}
	
	public int countOffset(int page) {
		int offset=(page-1)*this.limit;
		return offset;
	}
	
	public String showPaging(int pagelink, String pagename) {
		String str="";
		
		str="<table width=\"400\" border=\"0\" align=\"center\">";
		str=str+"<tr><td align=\"center\" bgcolor=\"#FFFFFF\">";
		
		int bpage=0;
		int boffset = 0;
		
		int cpage=0;
		int coffset = 0;
		
		int vpage=0;
		int noffset = 0;
		
		int loffset = 0;
		
		if(pagelink != 1) {
			str = str+"[<a href="+pagename+"?pagelink=1&offset=0><font size=2 color=red>¢¸¢¸</font></a>]";
		}
		else{
			str = str+"[<font size=2 color=gray>¢¸¢¸</font>]";
		}
		////////////////////////////////////////////////////
		bpage=pagelink - 10;
		boffset = countOffset(bpage);
		if(pagelink > links) {
			str = str+"[<a href="+pagename+"?pagelink="+bpage+"&offset="+boffset+"><font size=2 color=blue>¢¸</font></a>]";
		}
		else{
			str = str+"[<font size=2 color=gray>¢¸</font>]";
		}
		////////////////////////////////////////////////////
		vpage = pagelink;
		pagelink = ((pagelink-1)/links)*links+1;
		for(cpage=pagelink; cpage < pagelink+links; cpage++) {
			if(cpage > totalPage) {
				break;
			}
			coffset = countOffset(cpage);
			if(cpage != vpage) {
				str = str+"<a href="+pagename+"?pagelink="+cpage+"&offset="+coffset+"><font size=2 color=black>&nbsp;"+cpage+"&nbsp;</font></a>";
			}
			else {
				str = str+"<font size=2 color=red>&nbsp;"+cpage+"&nbsp;</font>";
			}
		}
		////////////////////////////////////////////////////
		noffset=countOffset(cpage);
		if((totalPage-pagelink) >= links) {
			str = str+"[<a href="+pagename+"?pagelink="+cpage+"&offset="+noffset+"><font size=2 color=blue>¢º</font></a>]";
		}
		else {
			str = str+"[<font size=2 color=gray>¢º</font>]";
		}
		////////////////////////////////////////////////////
		loffset=countOffset(totalPage);
		if(vpage!=totalPage) {
			str = str+"[<a href="+pagename+"?pagelink="+totalPage+"&offset="+loffset+"><font size=2 color=red>¢º¢º</font></a>]";
		}
		else {
			str = str+"[<font size=2 color=gray>¢º¢º</font>]";
		}
		
		str += "</td></tr></table>";
		return str;
	}
	
	public String showPaging(int pagelink, String pagename, String find, String search) {
		String str="";
		
		str="<table width=\"400\" border=\"0\" align=\"center\">";
		str=str+"<tr><td align=\"center\" bgcolor=\"#FFFFFF\">";
		
		int bpage=0;
		int boffset = 0;
		
		int cpage=0;
		int coffset = 0;
		
		int vpage=0;
		int noffset = 0;
		
		int loffset = 0;
		
		if(pagelink != 1) {
			str = str+"[<a href="+pagename+"?pagelink=1&offset=0&find="+find+"&search="+search+"><font size=2 color=red>¢¸¢¸</font></a>]";
		}
		else{
			str = str+"[<font size=2 color=gray>¢¸¢¸</font>]";
		}
		////////////////////////////////////////////////////
		bpage=pagelink - 10;
		boffset = countOffset(bpage);
		if(pagelink > links) {
			str = str+"[<a href="+pagename+"?pagelink="+bpage+"&offset="+boffset+"&find="+find+"&search="+search+"><font size=2 color=blue>¢¸</font></a>]";
		}
		else{
			str = str+"[<font size=2 color=gray>¢¸</font>]";
		}
		////////////////////////////////////////////////////
		vpage = pagelink;
		pagelink = ((pagelink-1)/links)*links+1;
		for(cpage=pagelink; cpage < pagelink+links; cpage++) {
			if(cpage > totalPage) {
				break;
			}
			coffset = countOffset(cpage);
			if(cpage != vpage) {
				str = str+"[<a href="+pagename+"?pagelink="+cpage+"&offset="+coffset+"&find="+find+"&search="+search+"><font size=2 color=black>&nbsp;"+cpage+"&nbsp;</font></a>]";
			}
			else {
				str = str+"[<font size=2 color=red>&nbsp;"+cpage+"&nbsp;</font>]";
			}
		}
		////////////////////////////////////////////////////
		noffset=countOffset(cpage);
		if((totalPage-pagelink) >= links) {
			str = str+"[<a href="+pagename+"?pagelink="+cpage+"&offset="+noffset+"&find="+find+"&search="+search+"><font size=2 color=blue>¢º</font></a>]";
		}
		else {
			str = str+"[<font size=2 color=gray>¢º</font>]";
		}
		////////////////////////////////////////////////////
		loffset=countOffset(totalPage);
		if(vpage!=totalPage) {
			str = str+"[<a href="+pagename+"?pagelink="+totalPage+"&offset="+loffset+"&find="+find+"&search="+search+"><font size=2 color=red>¢º¢º</font></a>]";
		}
		else {
			str = str+"[<font size=2 color=gray>¢º¢º</font>]";
		}
		
		str += "</td></tr></table>";
		return str;
	}
}
