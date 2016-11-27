#include "Frame.h"

Frame::Frame(int r,int c,int bw,int bh,int xf,int yf)
	:row(r),col(c),blockw(bw),blockh(bh),xoff(xf),yoff(yf)
{}




void Frame::draw_block(SDL_Surface *canvas,int x,int y,int w,int h,Uint32 color_light,Uint32 color,Uint32 color_dark)
{
	Draw_FillRect(canvas,x,y,w-2,2,color_light);
	Draw_FillRect(canvas,x,y+2,2,h-4,color_light);
	Draw_FillRect(canvas,x+2,y+2,w-4,h-4,color);
	Draw_FillRect(canvas,x+w-2,y,2,h,color_dark);
	Draw_FillRect(canvas,x,y+h-2,w-2,2,color_dark);
}

void Frame::put_wall(SDL_Surface *canvas)
{
	for(int i=0;i<col;++i)
	{
		draw_block(canvas,xoff+i*blockw,yoff,blockw,blockh,0xF7B33F,0xA6782A,0x503A14);
		draw_block(canvas,xoff+i*blockw,yoff+(row-1)*blockh,blockw,blockh,0xF7B33F,0xA6782A,0x503A14);

	}
	for(int i=1;i<row;++i)
	{
		draw_block(canvas,xoff,yoff+i*blockh,blockw,blockh,0xF7B33F,0xA6782A,0x503A14);
		draw_block(canvas,xoff+(col-1)*blockw,yoff+i*blockh,blockw,blockh,0xF7B33F,0xA6782A,0x503A14);
	}
}

void Frame::draw_food(SDL_Surface *canvas,int x,int y,int w,int h,Uint32 color)
{
	Draw_FillEllipse(canvas,x+w/2,y+h/2,w/2,h/2,color);
}

void Frame::put_food(SDL_Surface *canvas,vector<int>::const_iterator beg,vector<int>::const_iterator end)
{
	draw_food(canvas,xoff+blockw+foodpos%(col-2)*blockw,yoff+blockh+foodpos/(col-2)*blockh,blockw,blockh,0xFF0000);
}

void Frame::gen_food(vector<int>::const_iterator beg,vector<int>::const_iterator end)
{
	srand(static_cast<unsigned>(SDL_GetTicks()));
	while(true)
	{
		foodpos = rand()%((row-2)*(col-2));
		if(!count(beg,end,foodpos)) break;
	}
}