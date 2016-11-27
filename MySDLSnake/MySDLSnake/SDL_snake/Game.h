#ifndef __GAME_H__
#define __GAME_H__

#include "StdAfx.h"
#include "Frame.h"
#include "Snake.h"

class Game {
	friend class Frame;
	friend class Snake;
public:
	int *score;
	Game(int w,int h,int r,int c,int *s);
	bool init();
	bool handle_events();
	void start();
	void draw_failure();
	void draw_pause();
	void clean_up();
	~Game();
	static Uint32 INTERVAL;
	int verifycode(int i);
	
private:
	STATE state;
	SDL_Surface *canvas,*failure,*msg,*scores,*background;
	TTF_Font *font;
	Mix_Music *eat,*gameover;
	char *buffer;
	Frame *frame;
	Snake *snake;
	SDL_Event event;
	int width,height,row,col;
	bool RUN;


};

#endif //__GAME_H__