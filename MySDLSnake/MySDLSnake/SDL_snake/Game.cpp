#include "Game.h"
#include "Snake.h"
#include<iostream>
using namespace std;
Game::Game(int w,int h,int r,int c,int *s)
	:canvas(NULL),failure(NULL),scores(NULL),font(NULL),eat(NULL),gameover(NULL),buffer(NULL),frame(NULL),snake(NULL),width(w),height(h),row(r),col(c),RUN(false),state(OKM),msg(NULL),background(NULL)
{
	score = s;
}

Uint32 Game::INTERVAL = 1000;

bool Game::init()
{
	if(SDL_Init(SDL_INIT_EVERYTHING)==-1) return false;
	SDL_WM_SetCaption("GreedSnake",NULL);
	canvas = SDL_SetVideoMode(width,height,32,SDL_HWSURFACE);//在显存里头创建缓存//创建了窗口
	if(canvas==NULL) return false;
	if(TTF_Init()==-1) return false;
	font = TTF_OpenFont("Roguee.ttf",20);
	if(font==NULL) return false;
	if(Mix_Init(MIX_INIT_MP3)==0) return false;
	if(Mix_OpenAudio(22050,MIX_DEFAULT_FORMAT,2,4096)==-1) return false;
	gameover = Mix_LoadMUS("gameover.mp3");
	if(gameover==NULL) return false;
	eat = Mix_LoadMUS("eat.mp3");
	if(eat==NULL) return false;
	frame = new Frame(row,col,width/col,height/row,(width-width/col*col)/2,(height-height/row*row)/2);
	snake = new Snake(row,col,width/col,height/row,(width-width/col*col)/2,(height-height/row*row)/2);
	buffer = new char[256];
	background = SDL_LoadBMP("bg.bmp");
	return true;
}


int Game::verifycode(int i)
{
	int a,b,c,d;
    a = rand()%10;
    b = rand()%10;
    c = rand()%1;
    if(c == 0)//表示加法
  {
     cout<<a<<"+"<<b<<"="<<endl;
	 cin>>i;
	 if(i==(a+b))
	 {
		 d=1;
      }
	 else
	 {
		 d=0;
	 }
  }
    else
    {
      cout<<a<<"-"<<b<<"="<<endl;
	  cin>>i;
	  if(i==(a+b))
	 {
		 d=1;
      }
	  else
	  {
		  d=0;
	  }
     }
	return d;
}
bool Game::handle_events()
{
	switch(event.type)
	{
	case SDL_QUIT:
		RUN = false;
		break;
	case SDL_KEYDOWN:
		{
			switch(event.key.keysym.sym)
			{
			case SDLK_UP:
				snake->change_dir(UP);
				break;
			case  SDLK_p:
				if (state != PAUSE) {
					state=PAUSE;
				} else {
					state=OKM;
				}
				break;
			case SDLK_q:
				exit(0);
				break;
			case SDLK_DOWN:
				snake->change_dir(DOWN);
				break;
			case SDLK_LEFT:
				snake->change_dir(LEFT);
				break;
			case SDLK_RIGHT:
				snake->change_dir(RIGHT);
				break;
			default:break;
			}
		}
		break;
	default:break;
	}
	return false;
}

void Game::start()
{
	RUN = true;
	state = OKM;
	Uint32 time0,time1;

	
	frame->gen_food((snake->body).begin(),(snake->body).end());

	while(RUN)
	{
		time0 = SDL_GetTicks();
		while(SDL_PollEvent(&event))
		{
			Game::handle_events();
		}

		//SDL_FillRect(canvas, NULL, 0);
		
		SDL_BlitSurface(background, NULL, canvas, NULL );

		frame->put_wall(canvas);
		frame->put_food(canvas,(snake->body).begin(),(snake->body).end());
		snake->draw_whole(canvas);

		if (state != PAUSE) {
			state = snake->move(canvas,frame->foodpos);
		} else {
			draw_pause();
		}

		if(state==ATW)
		{
			draw_failure();
			RUN = false;
		}
		else if(state==EFD)
		{
			Mix_PlayMusic(eat,1);
			frame->gen_food((snake->body).begin(),(snake->body).end());
			*score+=50;
		}
		SDL_Flip(canvas);
		time1 = SDL_GetTicks();
		if(time1-time0<INTERVAL) SDL_Delay(INTERVAL-time1+time0);
		//SDL_Delay(100);
	}
	clean_up();
}

void Game::draw_pause()
{
	SDL_Rect rect;
	SDL_Color color = {0xFF,0xFF,0xFF};
	if (msg == NULL) {
		msg = TTF_RenderText_Solid(font,"PAUSED!",color);
	}
	rect.x = (width-msg->w)/2;
	rect.y = (height-msg->h)/2-11;
	SDL_BlitSurface(msg,NULL,canvas,&rect);
}

void Game::draw_failure()
{
	SDL_Rect rect;
	SDL_Color color = {0xFF,0xFF,0xFF};
	sprintf(buffer,"Score:%d",*this->score);

	failure = TTF_RenderText_Solid(font,"GAME OVER!",color);
	scores = TTF_RenderText_Solid(font,buffer,color);
	rect.x = (width-failure->w)/2;
	rect.y = (height-failure->h)/2-11;
	SDL_BlitSurface(failure,NULL,canvas,&rect);
	rect.x = (width-scores->w)/2;
	rect.y+=22;
	SDL_BlitSurface(scores,NULL,canvas,&rect);
	Mix_PlayMusic(gameover,1);
	SDL_Flip(canvas);
	SDL_Delay(2000);
}

void Game::clean_up()
{
	SDL_FreeSurface(failure);
	SDL_FreeSurface(scores);
	TTF_CloseFont(font);
	Mix_FreeMusic(eat);
	Mix_FreeMusic(gameover);
	Mix_Quit();
	TTF_Quit();
	SDL_Quit();
}

Game::~Game()
{
	if(buffer!=NULL) delete buffer;
	if(frame!=NULL) delete frame;
	if(snake!=NULL) delete snake;
}