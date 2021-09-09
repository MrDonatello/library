import React, {Component} from 'react';
import './App.css';
import Home from "./Home";
import User from "./components/User";
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import UserEdit from "./components/UserEdit";
import Author from "./components/Author";
import AuthorEdit from "./components/AuthorEdit";
import Genre from "./components/Genre";
import GenreEdit from "./components/GenreEdit";
import Book from "./components/Book";
import BookEdit from "./components/BookEdit";
import BookAuthorAdd from "./components/BookAuthorAdd";
import BookAuthorRemove from "./components/BookAuthorRemove";

class App extends Component {
    render() {
        return (
            <Router>
                <div>
                    <hr/>
                    <Switch>
                        <Route path="/" exact={true}>
                            <Home/>
                        </Route>
                        <Route path='/library/user' exact={true}>
                            <User/>
                        </Route>
                        <Route path='/library/user/:id'>
                            <UserEdit/>
                        </Route>
                        <Route path='/library/author' exact={true}>
                            <Author/>
                        </Route>
                        <Route path='/library/author/:id'>
                            <AuthorEdit/>
                        </Route>
                        <Route path='/library/genre' exact={true}>
                            <Genre/>
                        </Route>
                        <Route path='/library/genre/:id'>
                            <GenreEdit/>
                        </Route>
                        <Route path='/library/book' exact={true}>
                            <Book/>
                        </Route>
                        <Route path='/library/book/:id'>
                            <BookEdit/>
                        </Route>
                        <Route path='/library/bookAuthor/add/:id'>
                            <BookAuthorAdd/>
                        </Route>
                        <Route path='/library/bookAuthor/remove/:id'>
                            <BookAuthorRemove/>
                        </Route>
                    </Switch>
                </div>
            </Router>
        )
    }
}

export default App;
