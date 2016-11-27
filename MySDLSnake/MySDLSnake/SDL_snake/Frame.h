#ifndef __FRAME_H__
#define __FRAME_H__

#include "StdAfx.h"
#include "Game.h"

class Frame {
	friend class Game;
public:
	Frame(int r,int c,int bw,int bh,int xf,int yf);
	inline void draw_block(SDL_Surface *canvas,int x,int y,int w,int h,Uint32 color_light,Uint32 color,Uint32 color_dark);
	void put_wall(SDL_Surface *canvas);
	inline void draw_food(SDL_Surface *canvas,int x,int y,int w,int h,Uint32 color);
	void put_food(SDL_Surface *canvas,vector<int>::const_iterator beg,vector<int>::const_iterator end);
	void gen_food(vector<int>::const_iterator beg,vector<int>::const_iterator end);

private:
	int row,col,blockw,blockh,xoff,yoff,foodpos;

};

#endif //__FRAME_H__