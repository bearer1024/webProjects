#ifndef __SNAKE_H__
#define __SNAKE_H__

#include "StdAfx.h"
#include "Game.h"

class Snake {
	friend class Game;
public:
	Snake(int r,int c,int bw,int bh,int xf,int yf);
	inline void draw_part(SDL_Surface *canvas,int x,int y,int w,int h,Uint32 color);
	void draw_whole(SDL_Surface *canvas);
	void change_dir(DIR drt);
	inline void update(SDL_Surface *canvas,int value,Uint32 color);
	STATE move(SDL_Surface *canvas,int foodpos);
	bool moved;
	int get_headpos() {
		return body.front();
	}
	int get_tailpos() {
		return body.back();
	}
private:
	vector<int> body;
	int row,col,blockw,blockh,xoff,yoff,len;
	DIR dir;
	//bool moved;

};

#endif //__SNAKE_H__