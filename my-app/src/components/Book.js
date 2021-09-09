import React from "react";
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import {Link} from 'react-router-dom';
import AppNavBar from "../AppNavBar";

class Book extends React.Component {


    constructor(props) {
        super(props);
        this.state = {
            books: []
        };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/library/book').then(response => response.json()).then(data => this.setState({books: data}));

    };

    async remove(id) {
        await fetch(`/library/book/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedBooks = [...this.state.books].filter(i => i.id !== id);
            this.setState({books: updatedBooks});
        });
    }

    async Add(id) {
        await fetch(`/library/book/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedBooks = [...this.state.books].filter(i => i.id !== id);
            this.setState({books: updatedBooks});
        });
    }

    render() {
        const {books, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const book = books.map(book => {
            return <tr key={book.id}>
                <td style={{whiteSpace: 'nowrap'}}>{book.title}</td>
                <td>{book.yearOfPublishing}</td>
                <td>{book.genre}</td>
                <td>{book.isbn}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/library/book/" + book.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(book.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="success" tag={Link} to={"/library/bookAuthor/add/" + book.id}>Add author</Button>
                        <Button size="sm" color="danger" tag={Link} to={"/library/bookAuthor/remove/" + book.id}>Remove author</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavBar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/library/book/new">Add Book</Button>
                    </div>
                    <h3>Book</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th>Title</th>
                            <th>Year of publishing</th>
                            <th>Genre</th>
                            <th>isbn</th>
                        </tr>
                        </thead>
                        <tbody>
                        {book}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default Book;