<script>
  function handleChange(input) {
    if (input.value < 1) input.value = 1;
    if (input.value > ${data.paginatedResponse.totalPageCount}) input.value = ${data.paginatedResponse.totalPageCount};
  }
</script>
<style>
.pad0A {
	width: 20%;
	margin-top: -77px;
	margin-left: 100%;
}
</style>

<div id="ObjectButtonDiv" class="btn-group">
	<c:if test="${not empty data.paginatedResponse.resultList}">
		<div style="text-align: left">
			<label for="exampleInputFile" style="display: block">${data.paginatedResponse.summary}</label>
			<ul class="pagination">
				<c:choose>
					<c:when test="${not empty data.paginatedResponse.firstPageUrl}">
						<li><a
							href="javascript:getUrl('${data.paginatedResponse.firstPageUrl}','page-content-holder')">
								<i class="glyph-icon icon-step-backward"></i>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="#"><i
								class="glyph-icon icon-step-backward"></i></a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty data.paginatedResponse.prvSetUrl}">
						<li><a
							href="javascript:getUrl('${data.paginatedResponse.prvSetUrl}','page-content-holder')">
								<i class="glyph-icon icon-backward"></i>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="#"><i
								class="glyph-icon icon-backward"></i></a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty data.paginatedResponse.prvPageUrl}">
						<li><a
							href="javascript:getUrl('${data.paginatedResponse.prvPageUrl}','page-content-holder')">
								<i class="glyph-icon icon-chevron-left"></i>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="#"><i
								class="glyph-icon icon-chevron-left"></i></a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach var="obj" items="${data.paginatedResponse.pageUrlList}"
					varStatus="loop">
					<c:choose>
						<c:when
							test="${data.paginatedResponse.reqPage eq (loop.index + data.paginatedResponse.currentSetStartPage)}">
							<li class="active"><a href="#"> ${loop.index + 1 + data.paginatedResponse.currentSetStartPage}
							</a></li>
						</c:when>
						<c:otherwise>
							<li><a class="btn btn-default"
								href="javascript:getUrl('${obj}','page-content-holder');">
									${loop.index + 1 + data.paginatedResponse.currentSetStartPage}
							</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${not empty data.paginatedResponse.nxtPageUrl}">
						<li><a
							href="javascript:getUrl('${data.paginatedResponse.nxtPageUrl}','page-content-holder');">
								<i class="glyph-icon icon-chevron-right"></i>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="#"> <i
								class="glyph-icon icon-chevron-right"></i>
						</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty data.paginatedResponse.nxtSetUrl}">
						<li><a
							href="javascript:getUrl('${data.paginatedResponse.nxtSetUrl}','page-content-holder');">
								<i class="glyph-icon icon-forward"></i>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="#"> <i
								class="glyph-icon icon-forward"></i>
						</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty data.paginatedResponse.lastPageUrl}">
						<li><a
							href="javascript:getUrl('${data.paginatedResponse.lastPageUrl}','page-content-holder');">
								<i class="glyph-icon icon-step-forward"></i>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled"><a href="#"> <i
								class="glyph-icon icon-step-forward"></i>
						</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<div class="float-right col-md-4 pad0A padtenPercentagae">
				<strong>Goto page</strong>
				<div class="input-group">
					<input type="text" id="gotoPageNumber" class="form-control"
						value="${data.paginatedResponse.reqPage + 1}"
						onblur="handleChange(this);">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default"
							value="${data.paginatedResponse.reqPage}"
							onclick="javascript:getToPageUrl('${data.paginatedResponse.requestUriTmpl}',
								'gotoPageNumber',
								'${data.paginatedResponse.pageSize}',
								'page-content-holder');">
							<i class="glyph-icon icon-search"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
	</c:if>
</div>