
# Supervisor Scope

In this project, we use **`supervisorScope`** to handle multiple parallel tasks, ensuring that if one task fails, it doesn't affect the other tasks. The use of `supervisorScope` in Kotlin Coroutines ensures that coroutines launched within it are handled independently of each other. This is especially useful in scenarios where multiple tasks are running concurrently, and you don’t want the failure of one task to cancel other tasks.

### What is `supervisorScope`?
`supervisorScope` is a special coroutine scope that allows launching multiple child coroutines, and if one of them fails, the others are not cancelled automatically. It is similar to the regular coroutine scope, but with the added benefit that failures in one child coroutine don't affect other coroutines.

- **Usage**: It's used when you want to launch independent tasks in parallel but don't want one failure to propagate and cancel the others.
- **Example Use Case**: In our case, we are fetching user profile and posts from an API. If fetching the user profile fails, we still want to proceed with fetching the posts, and vice versa.

### Why use `supervisorScope`?
In standard coroutine scopes (like `launch` or `async`), if one coroutine fails, it cancels all the other coroutines running within the same scope. This may not be desirable when the tasks are independent of each other. **`supervisorScope`** ensures that the failure of one task does not affect other parallel tasks.

In this project:
- We launch two parallel tasks: one to fetch the user profile and another to fetch the user posts.
- If either task fails, the other continues, and the error is reported via a LiveData error message.


## User Dashboard with Supervisor Scope

This is a simple Android project using **Jetpack Compose**, **Kotlin Coroutines**, and **Retrofit** to fetch user profile and posts from a mock API. It demonstrates the use of **`supervisorScope`** for handling multiple parallel tasks (fetching user profile and posts), ensuring that one task's failure doesn't affect the other.

## Features
- Fetch user profile and posts in parallel using Kotlin coroutines.
- Display user profile details (name, email).
- Display user posts (title and body).
- Handle errors gracefully with meaningful error messages.

## Technologies Used
- **Jetpack Compose**: For UI development.
- **Kotlin Coroutines**: For asynchronous operations and parallel task handling.
- **Retrofit**: For making API calls to fetch data.
- **LiveData**: For observing data changes and updating the UI.
- **ViewModel**: For managing UI-related data in a lifecycle-conscious way.
- **Supervisor Scope**: To manage parallel coroutines independently.

## Project Structure

### 1. **UserViewModel**
The `UserViewModel` is responsible for:
- Fetching user profile and posts asynchronously using **Retrofit**.
- Managing the UI state with `LiveData` (e.g., user profile, posts, error messages).
- Using **`supervisorScope`** to handle parallel tasks (fetching profile and posts) where one task’s failure does not impact the other.

### 2. **UserDashboardScreen**
The `UserDashboardScreen` composable:
- Observes LiveData for user profile, posts, and error messages.
- Displays the user profile (name, email) and posts (title, body) using **Jetpack Compose**.
- Uses a `LazyColumn` to display a list of user posts.
- Displays error messages in case of failures.

### 3. **ApiService**
The `ApiService` interface defines the Retrofit API calls to fetch:
- User profile.
- User posts.

### 4. **MainActivity**
The `MainActivity` sets up the app’s user interface and handles the **Jetpack Compose** layout. It also passes the `UserDashboardScreen` a `modifier` to apply padding in the layout.

