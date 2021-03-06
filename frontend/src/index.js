// Set up your application entry point here...

import React from 'react';
import {render} from 'react-dom';
import {AppContainer} from 'react-hot-loader';
import {Root} from "./components/Root";
import configureStore from "./store/configureStore";
import {booksApi, BooksApiService} from "./services/BooksApiService";
import './styles/book.scss';

const store = configureStore();

booksApi.booksApiService = new BooksApiService();

render(
  <AppContainer>
    <Root store={store}/>
  </AppContainer>,
  document.getElementById('app')
);

if (module.hot) {
  module.hot.accept('./components/Root', () => {
    const NewRoot = require('./components/Root').Root;
    render(
      <AppContainer>
        <NewRoot/>
      </AppContainer>,
      document.getElementById('app')
    );
  });
}
