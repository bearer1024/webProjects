#include "Snake.h"

Snake::Snake(int r,int c,int bw,int bh,int xf,int yf)
	:row(r),col(c),blockw(bw),blockh(bh),xoff(xf),yoff(yf),len(3),dir(RIGHT),moved(true)
{
	body.push_back(2);
	body.push_back(1);
	body.push_back(0);
}

void Snake::draw_part(SDL_Surface *canvas,int x,int y,int w,int h,Uint32 color)
{
	Draw_FillRect(canvas,x,y,w,h,color);
}

void Snake::draw_whole(SDL_Surface *canvas)
{
	update(canvas, get_headpos(),0xFF0000);
	update(canvas, get_tailpos(),0x000000);
	for(vector<int>::size_type index=1;index<len-1;++index)
	{
		int value = body[index],x = value%(col-2)*blockw,y = value/(col-2)*blockh;
		draw_part(canvas,xoff+blockw+x,yoff+blockh+y,blockw,blockh,0x00FF00);
	}
}

void Snake::change_dir(DIR drt)
{
	if(!moved) return;
	if(drt==UP&&dir!=DOWN) dir = drt;
	else if(drt==DOWN&&dir!=UP) dir = drt;
	else if(drt==LEFT&&dir!=RIGHT) dir = drt;
	else if(drt==RIGHT&&dir!=LEFT) dir = drt;
	moved = false;
}



void Snake::update(SDL_Surface *canvas,int value,Uint32 color)
{
	int x = xoff+blockw+value%(col-2)*blockw,y = yoff+blockh+value/(col-2)*blockh;
	draw_part(canvas,x,y,blockw,blockh,color);
}

STATE Snake::move(SDL_Surface *canvas,int foodpos)
{
	int tail = body[len-1];

	for(vector<int>::size_type index=len-1;index!=0;--index)
	{
		body[index] = body[index-1];
	}
	// 1. 根据头的方向，判断是否撞墙
	// 2. 根据头的方向，计算出下一步的坐标
	// 3. 使用 count 函数，计算出整个蛇的坐标是否和下一步坐标一样，若有一个坐标和下一步坐标一样，则说明撞到了自己
	if(dir==UP)
	{
		if(body[0]-col+2<0||count(body.begin(),body.end(),body[0]-col+2)) return ATW;
		body[0]-=col-2;
	}
	else if(dir==DOWN)
	{
		if(body[0]+col-2>=(row-2)*(col-2)||count(body.begin(),body.end(),body[0]+col-2)) return ATW;
		body[0]+=col-2;
	}
	else if(dir==LEFT)
	{
		if(body[0]%(col-2)==0||count(body.begin(),body.end(),body[0]-1)) return ATW;
		body[0]-=1;
	}
	else
	{
		if((body[0]+1)%(col-2)==0||count(body.begin(),body.end(),body[0]+1)) return ATW;
		body[0]+=1;
	}
	moved = true;
	if(body[0]==foodpos)
	{
		body.push_back(tail);
		len+=1;
		return EFD;
	}
	return OKM;
}