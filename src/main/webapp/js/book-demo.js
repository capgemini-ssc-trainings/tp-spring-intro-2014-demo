angular.module('book-demo', ['ui.bootstrap']) //
.controller('BookSearchCntl',
		[ '$scope', 'books', '$modal', function($scope, books, $modal) {
			var openCurrentBookForEditAndRefreshOnClose = function() {
				var modalInstance = $modal.open({
					 controller : 'BookEditCntl', 
					 templateUrl: 'book-edit.html'
						 });
				modalInstance.result.then(function () {
					$scope.search();
				 });
				},
				openSpinner = function() {
					return $modal.open({
						windowClass:'spinner-modal',
						templateUrl: 'loader.html'});
				};
			$scope.searchCriteria = books.getSearchCriteria();
			$scope.resultList = books.getSearchResultList();
			$scope.search = function() {
				var modalInstance = openSpinner(),
					searchPromise = books.search();
				searchPromise.then(function() {
					modalInstance.close();
				});
			};
			$scope.deleteBook = function(book) {
				var promise = books.deleteBook(book);
				promise.then(function () {
					$scope.search();
				 });
			};
			$scope.addNewBook = function() {
				books.prepareNewBookForEdit();
				openCurrentBookForEditAndRefreshOnClose();
			};
			$scope.editBook = function(book) {
				var modalInstance = openSpinner(),
					loadPromise = books.loadBookForEdit(book.id);
				loadPromise.then(function() {
					modalInstance.close();
					openCurrentBookForEditAndRefreshOnClose();
				});
				
			};
			$scope.anyResultFound = function() {
				return $scope.resultList && $scope.resultList.length > 0;
			};
		} ]) //
.controller('BookEditCntl', ['$scope', '$modalInstance', 'books', function ($scope, $modalInstance, books) {
	$scope.book = books.getBookForEdit();
	$scope.saveBookAndClose = function() {
		var promise = books.saveOrUpdateEditedBook();
		promise.then(function() {
			$modalInstance.close();}
		);
	};
	$scope.cancel = function() {
		$modalInstance.dismiss();
	};
}]) //		
.factory('books', ['$http', function($http) {
	var searchCriteria = {}, resultList = [], editedBook = {},
	clearEditedBook = function() {editedBook = {};};

	return {
		search : function() {
			return $http.get('books', {
				params : searchCriteria
			}).success(function(data, status, headers, config) {
				angular.copy(data, resultList);
			});
		},
		deleteBook : function(book) {
			return $http.delete('books/' + book.id);
		},
		saveOrUpdateEditedBook : function() {
			return $http.post('books', editedBook);
		},
		getSearchCriteria : function() {
			return searchCriteria;
		},
		getSearchResultList : function() {
			return resultList;
		},
		getBookForEdit : function() {
			return editedBook;
		},
		prepareNewBookForEdit : function() {
			clearEditedBook();
		},
		loadBookForEdit : function(bookId) {
			clearEditedBook();
			return $http.get('books/' + bookId).success(function(data, status, headers, config) {
				angular.copy(data, editedBook);
				});
		}
	};
} ]);