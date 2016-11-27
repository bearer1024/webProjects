#ifndef __STDAFX_H__
#define __STDAFX_H__

#include <SDL.h>
#include <SDL_draw.h>
#include <SDL_ttf.h>
#include <SDL_mixer.h>
#include <cstdlib>
#include <ctime>
#include <vector>
#include <algorithm>


using std::vector;
using std::srand;
using std::rand;
using std::time;
using std::count;


enum DIR { UP, DOWN, LEFT, RIGHT };
enum STATE { OKM, ATW, EFD, PAUSE };


class Game;
class Frame;
class Snake;

#endif //__STDAFX_H__