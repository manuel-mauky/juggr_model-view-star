This example shows the MVC design pattern at the level of a single widget, in this case a "textfield".

The **View** contains of a white rectangle with a grey border and shows text inside of the rectangle.

The **Model** contains the state of the widget: The current Text that was entered by the user and a boolean flag
indicating whether or not the widget has the focus at the moment.

The **Controller** has the task to react on Input-Listeners from the View when the user presses keys on the keyboard.
It has to find out if a letter key or the backspace key was pressed. It then updates the model accordingly.
