# ----------------------------------------------------------------------
# Name:        matchit
# Purpose:     Implement a single player matching game
#
# Author(s):
# ----------------------------------------------------------------------
"""
A single player matching game.

usage: matchit.py [-h] [-f] {blue,green,magenta} image_folder
positional arguments:
  {blue,green,magenta}  What color would you like for the player?
  image_folder          What folder contains the game images?

optional arguments:
  -h, --help            show this help message and exit
  -f, --fast            Fast or slow game?
"""
import tkinter
import os
import random
import argparse


class MatchGame(object):

    """
    GUI Game class for a matching game.

    Arguments:
    parent: the root window object
    player_color (string): the color to be used for the matched tiles
    folder (string) the folder containing the images for the game
    delay (integer) how many milliseconds to wait before flipping a tile

    Attributes:
    Please list ALL the instance variables here
    """

    # Add your class variables if needed here - square size, etc...)

    def __init__(self, parent, player_color, folder, delay):
        self.score = 100
        self.flip = 0
        parent.title('Match it!')
        self.parent = parent
        self.display_list = list()
        self.selected_image = list()
        self.color = player_color
        # Create the restart button widget
        # Create a canvas widget
        # Create a label widget for the score and end of game messages
        # Create any additional instance variable you need for the game
        restart_button = tkinter.Button(parent, text='RESTART', width=20,
                                        command=self.restart)
        restart_button.grid()
        self.canvas = tkinter.Canvas(parent, width=400, height=400,
                                     background='white')
        for y in range(0, 400, 100):
            for x in range(0, 400, 100):
                self.canvas.create_rectangle(x, y, x+100, y+100, fill='yellow')
        self.file = [pic for pic in os.listdir(folder)
                     if pic[-3:] == 'gif'][0:8]
        os.chdir(folder)
        self.image_list = [tkinter.PhotoImage(file=i) for i in self.file]
        self.image_list = [i for i in self.image_list for _ in range(2)]
        random.shuffle(self.image_list)
        self.delay = delay

        self.canvas.bind("<Button-1>", self.play)
        self.canvas.grid()
        self.score_label = tkinter.Label(parent, text=f'Score: {self.score}')
        self.score_label.grid()

    def restart(self):
        """
        This method is invoked when player clicks on the RESTART button.
        It shuffles and reassigns the images and resets the GUI and the
        score.
        :return: None
        """
        self.display_list = list()
        self.selected_image = list()
        self.score = 100
        self.flip = 0
        self.score_label.configure(text=f'Score: {self.score}')
        random.shuffle(self.image_list)
        for each_shape in self.canvas.find_withtag("done") or \
                          self.canvas.find_withtag("selected"):
            self.canvas.itemconfig(each_shape, fill='yellow')
            self.canvas.itemconfig(each_shape, tag="")

    def play(self, event):
        """
        This method is invoked when the user clicks on a square.
        It implements the basic controls of the game.
        :param event: event (Event object) describing the click event
        :return: None
        """
        shape = self.canvas.find_closest(event.x, event.y)
        shape_id, = shape
        a, b, c, d = self.canvas.coords(shape)
        shape_x = (a + c) / 2
        shape_y = (b + d) / 2
        self.canvas.itemconfig(shape, tag="selected")
        image_id = self.canvas.create_image(shape_x, shape_y,
                                        image=self.image_list[shape_id - 1])
        self.display_list.append(image_id)
        self.selected_image.append(self.image_list[shape_id - 1])
        if len(self.selected_image) == 2:
            self.flip += 1
            if self.delay is True:
                self.canvas.after(1000, self.disappear)
            else:
                self.canvas.after(3000, self.disappear)

        if self.flip > 13:
            self.score = 100 - 10 * (self.flip - 13)
            self.score_label.configure(text=f'Score: {self.score}')

    def disappear(self):
        """
        Remove image in display list from the Canvas
        """
        for i in self.display_list:
            self.canvas.delete(i)
        if self.selected_image[0] == self.selected_image[1]:
            for each_shape in self.canvas.find_withtag("selected"):
                self.canvas.itemconfig(each_shape, fill=self.color)
                self.canvas.itemconfig(each_shape, tag="done")
        else:
            for each_shape in self.canvas.find_withtag("selected"):
                self.canvas.itemconfig(each_shape, tag="")
        self.selected_image = list()

    # Enter your additional method definitions below
    # Make sure they are indented inside the MatchGame class
    # Make sure you include docstrings for all the methods.


# Enter any function definitions here to get and validate the
# command line arguments.  Include docstrings.
def scale_type(entered_scale):
    """
    Validate the user entered scale and return the file with at least 8 images
    :param entered_scale : (string)
    :return: string
    """
    try:
        os.listdir(entered_scale)
    except FileNotFoundError:
        raise argparse.ArgumentTypeError(f"{entered_scale} "
                                         f"is not a valid folder")
    else:
        for dirpath, dirnames, filenames in os.walk(top=entered_scale):
            if len([word for word in filenames if word[-3:] == 'gif']) < 8:
                raise argparse.ArgumentTypeError(f"{entered_scale} "
                                        f"must contain at least 8 gif images")
            return entered_scale


def get_arguments():
    """
    Parse and validate the command line arguments.
    :return: tuple containing the color (string), folder (string)
             and the fast option (boolean).
    """
    parser = argparse.ArgumentParser()
    parser.add_argument('color',
                        help='What color would you like for the player?',
                        choices=['blue', 'green', 'magenta'])

    parser.add_argument('folder',
                        help='What folder contains the game images?',
                        type=scale_type)

    parser.add_argument('-f', '--fast',
                        help='Fast or slow game?',
                        action='store_true')

    arguments = parser.parse_args()
    return arguments.color, arguments.folder, arguments.fast


def main():
    # Retrieve and validate the command line arguments using argparse
    # Instantiate a root window
    # Instantiate a MatchGame object with the correct arguments
    # Enter the main event loop
    color, folder, fast = get_arguments()
    root = tkinter.Tk()
    match_game = MatchGame(root, color, folder, fast)
    root.mainloop()


if __name__ == '__main__':
    main()