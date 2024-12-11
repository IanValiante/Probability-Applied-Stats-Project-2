% Define the function
f = @(x) x.^2 + 2*x + 1;

% Create a range of x values
x = -10:0.1:10;

% Compute the corresponding y values from the function
y = f(x);

% Add random noise (salted data)
noise = 2 * randn(size(y)); % Adjust the noise magnitude as needed
y_salted = y + noise;

% Smooth the salted data using the 'smooth' function
y_smooth = smooth(x, y_salted, 0.1, 'loess');  % 'loess' uses local regression

% Plot the original, salted, and smoothed data
figure;
plot(x, y, '-', 'LineWidth', 2, 'DisplayName', 'Original Function'); % Default plot line
hold on;
plot(x, y_salted, 'o', 'DisplayName', 'Salted Data'); % Noisy data
plot(x, y_smooth, '-', 'LineWidth', 2, 'DisplayName', 'Smoothed Data'); % Smoothed data
xlabel('x');
ylabel('y');
title('Data Plot with Original, Salted, and Smoothed Data');
legend;
grid on;