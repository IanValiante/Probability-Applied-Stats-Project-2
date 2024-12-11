% Define the function
f = @(x) x.^2 + 2*x + 1;

% Create a range of x values
x = -10:0.1:10;

% Compute the corresponding y values
y = f(x);

% Plot the function
plot(x, y);
xlabel('x');
ylabel('f(x)');
title('Plot of f(x) = x^2 + 2x + 1');
grid on;