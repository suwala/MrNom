package com.badlogic.androidgames.framework.mrnom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.androidgames.framework.FileIO;

public class Settings {

	//サウンドONOFFのフラグ
	public static boolean soundEnabled = true;
	public static int[] highscores = new int[]{100,80,50,30,10};
	
	public static void load(FileIO files){
		BufferedReader in = null;
		try{
			in = new BufferedReader(new InputStreamReader(files.readFile(".mrnom")));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			for(int i=0;i<5;i++){
				highscores[i] = Integer.parseInt(in.readLine());
			}
		}catch (IOException e){
			//デフォルト設定ではエラーでるので無視
		}catch(NumberFormatException e){
			//同上
		}finally{
			try{
				if(in!=null)
					in.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void save(FileIO files){
		BufferedWriter out = null;
		try{
			out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".mrnom")));
			out.write(Boolean.toString(soundEnabled));
			for(int i=0;i<5;i++){
				out.write(Integer.toString(highscores[i]));
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(out != null)
					out.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void addScore(int score){
		for(int i=0;i<5;i++){
			if(highscores[i] < score){
				for(int j=4;j > i;j--)
					highscores[j] = highscores[j-1];
				highscores[i] = score;
				break;
			}
		}
	}
}
