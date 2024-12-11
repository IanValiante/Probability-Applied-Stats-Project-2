% Define the function
f = @(x) x.^2 + 2*x + 1;

% Create a range of x values
x = -10:0.1:10;

% Compute the corresponding y values from the function
y = f(x);

% Add random noise (salted data)
noise = 2 * randn(size(y)); % Adjust the noise magnitude as needed
y_salted = y + noise;

% Plot the salted data
plot(x, y_salted, 'o');
xlabel('x');
ylabel('y');
title('Salted Data Plot');
grid on;