package com.badlogic.androidgames.framework.mrnom;

import java.util.ArrayList;
import java.util.List;

public class Snake {
	
	public static final int UP =0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	
	//パーツの管理
	public List<SnakePart> parts = new ArrayList<SnakePart>();
	//進行方向を示す
	public int direction;
	
	public Snake() {
		//向きの設定
		direction = UP;
		//初期パーツの設定
		parts.add(new SnakePart(5,6));
		parts.add(new SnakePart(5,7));
		parts.add(new SnakePart(5, 8));
	}
	
	public void turnLeft(){
		direction += 1;
		if(direction > RIGHT)
			direction = UP;
	}
	
	public void turnRight(){
		direction -= 1;
		if(direction < UP)
			direction = RIGHT;
	}
	
	public void eat(){
		SnakePart end = parts.get(parts.size()-1);
		parts.add(new SnakePart(end.x,end.y));
	}
	
	//移動処理　頭からではなく尻尾から移動させるのがミソ
	public void advance(){
		SnakePart head = parts.get(0);
		
		int len = parts.size()-1;
		for(int i=len;i>0;i--){
			SnakePart before = parts.get(i-1);
			SnakePart part = parts.get(i);
			part.x = before.x;
			part.y = before.y;
		}
		
		if(direction == UP)
			head.y -= 1;
		if(direction == LEFT)
			head.x -= 1;
		if(direction == DOWN)
			head.y += 1;
		if(direction == RIGHT)
			head.x += 1;
		if(head.x < 0)
			head.x =9;
		if(head.x > 9)
			head.x = 0;
		if(head.y < 0)
			head.y = 12;
		if(head.y > 12)
			head.y = 0;
			
	}
	
	//衝突処理
	public boolean checkBitten(){
		int len = parts.size();
		SnakePart head = parts.get(0);
		for(int i=1;i<len;i++){
			SnakePart part = parts.get(i);
			if(part.x == head.x && part.y == head.y)
				return true;
		}
		return false;
	}

}
